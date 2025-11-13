package com.pnfsoftware.jeb.core.units.code.dart;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IDartAotSnapshotInfo {
   String getVersionTag();

   String getVersionHash();

   long getSnapshotSize();

   Set getFeatures();

   long getBaseObjectsCount();

   long getObjectsCount();

   List getInternalObjects();

   Map getClassIdNameMap();
}
