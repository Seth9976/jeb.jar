package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;
import java.util.List;

@Ser
public interface IPrimitiveTypeManager {
   IPrimitiveType addPrimitive(String var1, int var2, PrimitiveCategory var3);

   IPrimitiveSizes getSizes();

   Collection getTypes();

   Collection getNames();

   IPrimitiveType getType(String var1);

   IPrimitiveType getExactIntegerBySize(int var1, boolean var2);

   IPrimitiveType getIntegerBySize(int var1, boolean var2);

   IPrimitiveType getExactFloatBySize(int var1);

   boolean isVoid(IPrimitiveType var1);

   boolean isCharacter(IPrimitiveType var1);

   boolean isInteger(IPrimitiveType var1);

   boolean isSignedInteger(IPrimitiveType var1);

   boolean isUnsignedInteger(IPrimitiveType var1);

   boolean isFloat(IPrimitiveType var1);

   List getAlternateNames(IPrimitiveType var1);

   boolean addAlternateName(IPrimitiveType var1, String var2, boolean var3);

   boolean setEffectiveName(IPrimitiveType var1, String var2);
}
