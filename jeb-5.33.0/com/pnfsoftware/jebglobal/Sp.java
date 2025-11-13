package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;

public interface Sp {
   IEGeneric getRegisterVariableFromNativeRegisterId(long var1);

   long getNativeRegisterIdFromRegisterVariable(IEVar var1, boolean var2);

   int getStateProcessorMode(EState var1);

   IEGlobalContext getGlobalContext();

   IEVar sY();

   IEVar getProgramCounter();

   IEGeneric E();

   IEVar pC();

   int kS();

   IEGeneric kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3);

   void pC(Ro.K var1, long var2);

   void pC(IEGeneric var1, long var2);

   IEGeneric A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, IEGeneric var4);

   boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, Ro.K var4);

   IEGeneric A(IEGeneric var1);

   void pC(Ro.K var1, Yg var2);

   void A(Ro.K var1, Yg var2);
}
