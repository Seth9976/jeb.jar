package com.pnfsoftware.jeb.core.units.code.dart;

import com.pnfsoftware.jeb.core.units.IUnit;
import java.util.List;

public interface IDartAotUnit extends IUnit {
   IDartAotSnapshotInfo getVmSnapshotInfo();

   IDartAotSnapshotInfo getIsolateSnapshotInfo();

   List getInternalObjects();

   List generatePrimaryPool();
}
