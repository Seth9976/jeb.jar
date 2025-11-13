package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaConstantFactory {
   IJavaConstant createNull();

   default IJavaConstant createBoolean(boolean var1) {
      return this.createBoolean(var1, null);
   }

   IJavaConstant createBoolean(boolean var1, String var2);

   default IJavaConstant createByte(byte var1) {
      return this.createByte(var1, null);
   }

   IJavaConstant createByte(byte var1, String var2);

   default IJavaConstant createChar(char var1) {
      return this.createChar(var1, null);
   }

   IJavaConstant createChar(char var1, String var2);

   default IJavaConstant createShort(short var1) {
      return this.createShort(var1, null);
   }

   IJavaConstant createShort(short var1, String var2);

   default IJavaConstant createInt(int var1) {
      return this.createInt(var1, null);
   }

   IJavaConstant createInt(int var1, String var2);

   default IJavaConstant createLong(long var1) {
      return this.createLong(var1, null);
   }

   IJavaConstant createLong(long var1, String var3);

   default IJavaConstant createFloat(float var1) {
      return this.createFloat(var1, null);
   }

   IJavaConstant createFloat(float var1, String var2);

   default IJavaConstant createDouble(double var1) {
      return this.createDouble(var1, null);
   }

   IJavaConstant createDouble(double var1, String var3);

   default IJavaConstant createString(String var1) {
      return this.createString(var1, null);
   }

   IJavaConstant createString(String var1, String var2);
}
