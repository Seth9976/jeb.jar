package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IClassType extends IStructureType {
   INativeClassItem getClassItem();

   Collection getSupertypes();

   Collection getSubtypes();

   List getInstanceFields();

   List getVirtualTables();

   boolean renameVirtualMethod(int var1, int var2, String var3);

   boolean collectVirtualMethodOverrides(int var1, int var2, Collection var3, Collection var4);
}
