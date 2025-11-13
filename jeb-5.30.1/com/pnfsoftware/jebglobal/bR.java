package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;

public interface bR {
   IEGeneric getRegisterVariableFromNativeRegisterId(long var1);

   long getNativeRegisterIdFromRegisterVariable(IEVar var1, boolean var2);

   int getStateProcessorMode(EState var1);

   IEGlobalContext getGlobalContext();

   IEVar gO();

   IEVar getProgramCounter();

   IEGeneric oW();

   IEVar q();

   int xK();

   IEGeneric xK(fA var1, CW var2, long var3);

   void q(Ia.nI var1, long var2);

   void q(IEGeneric var1, long var2);

   IEGeneric RF(fA var1, long var2, IEGeneric var4);

   boolean q(fA var1, long var2, Ia.nI var4);

   IEGeneric RF(IEGeneric var1);

   void q(Ia.nI var1, CW var2);

   void RF(Ia.nI var1, CW var2);
}
