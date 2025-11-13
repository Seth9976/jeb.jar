package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface IJavaLabelFactory {
   IJavaLabel create(int var1, String var2);

   IJavaLabel create(int var1);

   IJavaLabel create();

   List getLabels();

   void recordTrampoline(int var1, int var2);

   boolean checkEquivalence(IJavaLabel var1, IJavaLabel var2);
}
