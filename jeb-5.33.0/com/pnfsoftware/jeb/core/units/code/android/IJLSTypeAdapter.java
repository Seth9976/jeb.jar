package com.pnfsoftware.jeb.core.units.code.android;

import java.util.List;

public interface IJLSTypeAdapter {
   IJLSType getType(String var1);

   String getSupertype(String var1);

   List getInterfaces(String var1);

   List getParentTypes(String var1);

   List getMethods(String var1);

   IJLSMethod findMethod(String var1, String var2, String var3);

   List getFields(String var1);

   List getTypeAnnotations(String var1);
}
