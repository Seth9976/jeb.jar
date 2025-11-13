package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaOperatorFactory {
   IJavaOperator getStandardOperator(JavaOperatorType var1);

   default IJavaOperator get(JavaOperatorType var1) {
      return this.getStandardOperator(var1);
   }

   IJavaOperator createCastOperator(IJavaType var1);

   IJavaTypeFactory getTypeFactory();
}
