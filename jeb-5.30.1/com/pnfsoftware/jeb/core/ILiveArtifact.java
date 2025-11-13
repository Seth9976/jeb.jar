package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.core.units.IUnit;
import java.util.List;

public interface ILiveArtifact {
   boolean load(String var1, boolean var2, boolean var3);

   IRuntimeProject getRuntimeProject();

   IArtifact getArtifact();

   List getUnits();

   IUnit getMainUnit();
}
