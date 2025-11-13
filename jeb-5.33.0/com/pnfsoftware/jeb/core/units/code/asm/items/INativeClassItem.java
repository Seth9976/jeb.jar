package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.ICodeClass;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface INativeClassItem extends ICodeClass, INativeItem {
   IClassType getClassType();

   @Override
   List getSupertypes();

   @Override
   List getImplementedInterfaces();

   @Override
   List getMethods();

   @Override
   List getFields();

   List getSubtypes();

   List getVirtualTables();

   List getVirtualMethods(boolean var1);

   List getNonVirtualMethods();

   List getStaticMethods();

   List getInstanceMethods();

   List getConstructors();

   List getDestructors();

   List getInstanceFields();

   List getStaticFields();

   INativeMethodItem getVirtualMethodFromCoordinates(int var1, int var2);

   Couple getCoordinatesOfVirtualMethod(INativeMethodItem var1);

   boolean renameVirtualMethod(INativeMethodItem var1, String var2);

   boolean collectVirtualMethodOverrides(INativeMethodItem var1, Collection var2, Collection var3);
}
