package com.pnfsoftware.jeb.core.units.code.android.dex;

import java.util.List;

public interface IDexAnnotationsDirectory {
   List getClassAnnotations();

   List getFieldsAnnotations();

   List getMethodsAnnotations();

   List getParametersAnnotations();

   List getFieldAnnotations(int var1);

   List getMethodAnnotations(int var1);

   List getParametersAnnotations(int var1);
}
