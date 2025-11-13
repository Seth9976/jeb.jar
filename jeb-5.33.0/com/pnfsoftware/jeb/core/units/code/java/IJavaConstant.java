package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaConstant extends IJavaExpression {
   void setName(String var1);

   String getName();

   IJavaType getType();

   boolean isNull();

   boolean isString();

   boolean getBoolean();

   byte getByte();

   char getChar();

   short getShort();

   int getInt();

   long getLong();

   float getFloat();

   double getDouble();

   String getString();

   boolean isTrue();

   boolean isFalse();

   boolean isZero();

   boolean isOne();

   boolean isMinusOne();

   boolean isPositive();

   boolean isNegative();

   IJavaConstant duplicate();
}
