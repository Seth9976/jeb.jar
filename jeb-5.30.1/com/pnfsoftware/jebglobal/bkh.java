package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.IJLSAnnotation;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class bkh implements IJLSAnnotation {
   String q;
   Map RF = new LinkedHashMap();

   public bkh(String var1, Map var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String getType() {
      return this.q;
   }

   @Override
   public Map getValues() {
      return Collections.unmodifiableMap(this.RF);
   }

   @Override
   public String toString() {
      return this.q + ", " + this.RF;
   }
}
