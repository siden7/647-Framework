package utilities.tools;

import java.io.IOException;
import java.util.Arrays;

import com.alex.store.Index;
import com.alex.store.Store;

public class MapRegionReplacer {

	private static final int[] REGION_IDS = { 9030 };

	public static void main(String[] args) throws IOException, InterruptedException {
		Store packTo = new Store(System.getProperty("user.home") + "/Documents/727Cache/");
		Store packFrom = new Store(System.getProperty("user.home") + "/Desktop/742Cache/");

		/* ----------------------------------------------------------------------------------------------------------------------- */

		System.out.println("Updating Maps.");
		for (int regionId : REGION_IDS) {
			int regionX = (regionId >> 8) * 64;
			int regionY = (regionId & 0xff) * 64;
			System.out.println("regionId: " + regionId);
			String name = new StringBuilder().append("m").append((regionX >> 3) / 8).append("_").append((regionY >> 3) / 8).toString();
			byte[] data = packFrom.getIndexes()[5].getFile(packFrom.getIndexes()[5].getArchiveId(name));
			if (data != null) {
				boolean result = addMapFile(packTo.getIndexes()[5], name, data);
				System.out.println(new StringBuilder().append(name).append(", ").append(result).toString());
			}
			name = new StringBuilder().append("um").append((regionX >> 3) / 8).append("_").append((regionY >> 3) / 8).toString();
			data = packFrom.getIndexes()[5].getFile(packFrom.getIndexes()[5].getArchiveId(name));
			if (data != null) {
				boolean result = addMapFile(packTo.getIndexes()[5], name, data);
				System.out.println(new StringBuilder().append(name).append(", ").append(result).toString());
			}
			int[] xteas = null;
			name = new StringBuilder().append("l").append((regionX >> 3) / 8).append("_").append((regionY >> 3) / 8).toString();
			data = packFrom.getIndexes()[5].getFile(packFrom.getIndexes()[5].getArchiveId(name), 0, xteas);
			System.out.println("valid: " + (data != null));
			System.out.println(Arrays.toString(xteas));
			if (data != null) {
				boolean result = addMapFile(packTo.getIndexes()[5], name, data);
				System.out.println(new StringBuilder().append(name).append(", ").append(result).toString());
			}
			name = new StringBuilder().append("ul").append((regionX >> 3) / 8).append("_").append((regionY >> 3) / 8).toString();
			data = packFrom.getIndexes()[5].getFile(packFrom.getIndexes()[5].getArchiveId(name), 0, xteas);
			if (data != null) {
				boolean result = addMapFile(packTo.getIndexes()[5], name, data);
				System.out.println(new StringBuilder().append(name).append(", ").append(result).toString());
			}
			name = new StringBuilder().append("n").append((regionX >> 3) / 8).append("_").append((regionY >> 3) / 8).toString();
			data = packFrom.getIndexes()[5].getFile(packFrom.getIndexes()[5].getArchiveId(name), 0);
			if (data != null) {
				boolean result = addMapFile(packTo.getIndexes()[5], name, data);
				System.out.println(new StringBuilder().append(name).append(", ").append(result).toString());
			}
			// Thread.sleep(50);
		}
		boolean result = packTo.getIndexes()[5].rewriteTable();
		System.out.println(new StringBuilder().append("Updated maps: ").append(result).toString());
	}

	public static boolean addMapFile(Index index, String name, byte[] data) {
		int archiveId = index.getArchiveId(name);
		if (archiveId == -1)
			archiveId = index.getTable().getValidArchiveIds().length;
		return index.putFile(archiveId, 0, 2, data, null, false, false, com.alex.utils.Utils.getNameHash(name), -1);
	}

}