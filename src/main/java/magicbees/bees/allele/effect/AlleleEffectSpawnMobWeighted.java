package magicbees.bees.allele.effect;

import magicbees.bees.AlleleEffect;
import magicbees.bees.EffectData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.genetics.IEffectData;

public class AlleleEffectSpawnMobWeighted extends AlleleEffect {
	private String[] entityNames;
	private int[] spawnChance;

	public AlleleEffectSpawnMobWeighted(String id, boolean isDominant, int throttle, String[] mobs, int[] chance) {
		super(id, isDominant, throttle);
		if (mobs.length != chance.length) {
			throw new IllegalArgumentException("Array lengths must be equal!");
		}
		this.entityNames = mobs;
		this.spawnChance = chance;
	}

	@Override
	public IEffectData validateStorage(IEffectData storedData) {
		if (storedData == null || !(storedData instanceof magicbees.bees.EffectData)) {
			storedData = new EffectData(1, 0, 0);
		}
		return storedData;
	}

	@Override
	public IEffectData doEffectThrottled(IBeeGenome genome, IEffectData storedData, IBeeHousing housing) {
		if (this.spawnMob(genome, housing)) {
			storedData.setInteger(0, 0);
		}

		return storedData;
	}

	protected boolean spawnMob(IBeeGenome genome, IBeeHousing housing) {
		boolean flag = false;

		World w = housing.getWorld();
		int roll = 0;

		for (int i = 0; i < this.spawnChance.length && !flag; ++i) {
			roll = 100 - w.rand.nextInt(100) + 1;
			if (roll < this.spawnChance[i]) {
				flag = true;
				Entity mob = EntityList.createEntityByName(this.entityNames[i], w);
				// .createEntityByName method returns null when spawning a ghast in the overworld
				if(mob == null)
					return false;
				double[] coords = this.randomMobSpawnCoords(w, genome, housing);

				mob.setPositionAndRotation(coords[0], coords[1], coords[2], w.rand.nextFloat() * 360f, 0f);
				if (mob instanceof EntityLiving) {
					if (((EntityLiving) mob).getCanSpawnHere()) {
						w.spawnEntityInWorld(mob);
					}
				} else {
					w.spawnEntityInWorld(mob);
				}
			}
		}

		return flag;
	}

	protected double[] randomMobSpawnCoords(World world, IBeeGenome bee, IBeeHousing housing) {
		double pos[] = new double[3];
		pos[0] = housing.getXCoord() + world.rand.nextDouble() * (bee.getTerritory()[0] * housing.getTerritoryModifier(bee, 1f)) - bee.getTerritory()[0] / 2;
		pos[1] = housing.getYCoord() + world.rand.nextDouble() * (bee.getTerritory()[1] * housing.getTerritoryModifier(bee, 1f)) - bee.getTerritory()[1] / 2;
		pos[2] = housing.getZCoord() + world.rand.nextDouble() * (bee.getTerritory()[2] * housing.getTerritoryModifier(bee, 1f)) - bee.getTerritory()[2] / 2;
		return pos;
	}

}
