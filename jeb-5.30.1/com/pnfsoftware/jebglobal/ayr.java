package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledRoutine;
import java.util.List;

public class ayr implements IUnmangledRoutine {
   private final String q;
   private final String RF;

   public ayr(String var1) {
      this(var1, var1);
   }

   public ayr(String var1, String var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public String getFull() {
      return this.RF;
   }

   @Override
   public String getSimple() {
      return this.q;
   }

   @Override
   public String getName() {
      return this.q;
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
