package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSAnnotation;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class bgm implements IJLSAnnotation {
   String pC;
   Map A = new LinkedHashMap();

   public bgm(String var1, Map var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String getType() {
      return this.pC;
   }

   @Override
   public Map getValues() {
      return Collections.unmodifiableMap(this.A);
   }

   @Override
   public String toString() {
      return this.pC + ", " + this.A;
   }
}
