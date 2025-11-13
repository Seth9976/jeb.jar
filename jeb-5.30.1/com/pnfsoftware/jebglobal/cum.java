package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;

public abstract class cum implements adk {
   INativeCodeAnalyzer q;
   ITypeManager RF;
   IPECOFFUnit xK;
   IVirtualMemory Dw;
   INativeType Uv;
   INativeType oW;
   INativeType gO;
   INativeType nf;
   INativeType gP;
   INativeType za;
   INativeType lm;
   INativeType zz;
   INativeType JY;
   INativeType HF;
   INativeType LK;
   INativeType io;
   INativeType qa;

   cum(INativeCodeAnalyzer var1) {
      this.q = var1;
      this.Dw = var1.getMemory();
      this.xK = (IPECOFFUnit)var1.getContainer();
      this.RF = var1.getTypeManager();
      this.Uv = this.RF.getType("UnwindMapEntry");
      this.oW = this.RF.getType("IptoStateMapEntry");
      this.gO = this.RF.getType("FuncInfo");
      this.nf = this.RF.getType("TryBlockMapEntry");
      this.gP = this.RF.getType("HandlerType");
      this.za = this.RF.getType("UnwindInfo");
      this.lm = this.RF.getType("UnwindCode");
      this.zz = this.RF.getType("ScopeTableEntry");
      this.JY = this.RF.getType("RUNTIME_FUNCTION");
      this.HF = this.RF.getType("_EH3_SCOPETABLE_ENTRY");
      this.LK = this.RF.getType("_EH4_SCOPETABLE_ENTRY");
      this.io = this.RF.getType("_EH4_SCOPETABLE");
      this.qa = conn.q(this.RF, var1.getProcessor().getMode() == 64);
   }
}
