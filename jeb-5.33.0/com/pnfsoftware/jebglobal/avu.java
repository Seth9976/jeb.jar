package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import java.util.List;

public class avu implements IUnmangledRoutine {
   private final String pC;
   private final String A;

   public avu(String var1, String var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String getFull() {
      return this.A;
   }

   @Override
   public String getSimple() {
      return this.pC;
   }

   @Override
   public String getName() {
      return this.pC;
   }

   @Override
   public boolean isRawData() {
      return false;
   }

   @Override
   public String getNameWithParameters(boolean var1) {
      return null;
   }

   @Override
   public String getReturnType() {
      return null;
   }

   @Override
   public List getParameterTypes() {
      return null;
   }

   @Override
   public List getAttributes() {
      return null;
   }

   @Override
   public String getCallingConvention() {
      return null;
   }

   @Override
   public String getSignature(boolean var1) {
      return null;
   }

   @Override
   public String getSignature(boolean var1, boolean var2) {
      return null;
   }

   @Override
   public String getPrototype(boolean var1) {
      return null;
   }

   @Override
   public String getPrototype(boolean var1, boolean var2) {
      return null;
   }
}
