package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface ICIdentifier extends ICLeftExpression {
   int FLAG_MEANINGFUL_NAME = 2;

   int getId();

   ICType getType();

   String getOriginalName();

   String getName();

   boolean setName(String var1);

   boolean setName(String var1, IDynamicContentManager var2);

   CIdentifierClass getIdentifierClass();

   Integer getMethodIndex();

   Integer getParameterIndex();

   Long getAddress();

   int getFlags();

   void setFlags(int var1);

   default boolean isParameter() {
      return this.getParameterIndex() != null;
   }

   void generate(COutputSink var1, boolean var2);

   ICIdentifier duplicate();
}
