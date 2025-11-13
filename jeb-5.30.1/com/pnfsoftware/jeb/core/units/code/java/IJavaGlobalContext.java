package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaGlobalContext extends IJavaElementFactory {
   IDexDecompilerUnit getDecompiler();

   IJavaTypeFactory getTypeFactory();

   IJavaOperatorFactory getOperatorFactory();

   IJavaConstantFactory getConstantFactory();

   IJavaClassFactory getClassFactory();

   IJavaMethodFactory getMethodFactory();

   IJavaFieldFactory getFieldFactory();

   IJMasterOptimizer createMasterOptimizer(IJavaDecompilableElement var1, boolean var2, boolean var3, boolean var4);

   default IJMasterOptimizer createMasterOptimizer(IJavaDecompilableElement var1) {
      return this.createMasterOptimizer(var1, true, true, true);
   }
}
