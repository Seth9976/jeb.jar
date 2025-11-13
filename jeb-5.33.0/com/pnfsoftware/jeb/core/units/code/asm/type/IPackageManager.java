package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IPackageManager {
   List getPackages();

   IPackage getPackageOfItem(INativeItem var1);

   IPackage createPackage(IPackage var1, String var2);

   IPackage createPackage(String var1);

   boolean moveToPackage(INativeItem var1, IPackage var2);

   boolean deletePackage(IPackage var1);

   boolean moveToClass(INativeItem var1, INativeClassItem var2);
}
