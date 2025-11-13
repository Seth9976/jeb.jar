package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.IPECOFFUnit;

public abstract class cjz implements abs {
   INativeCodeAnalyzer pC;
   ITypeManager A;
   IPECOFFUnit kS;
   IVirtualMemory wS;
   INativeType UT;
   INativeType E;
   INativeType sY;
   INativeType ys;
   INativeType ld;
   INativeType gp;
   INativeType oT;
   INativeType fI;
   INativeType WR;
   INativeType NS;
   INativeType vP;
   INativeType xC;
   INativeType ED;

   cjz(INativeCodeAnalyzer var1) {
      this.pC = var1;
      this.wS = var1.getMemory();
      this.kS = (IPECOFFUnit)var1.getContainer();
      this.A = var1.getTypeManager();
      this.UT = this.A.getType("UnwindMapEntry");
      this.E = this.A.getType("IptoStateMapEntry");
      this.sY = this.A.getType("FuncInfo");
      this.ys = this.A.getType("TryBlockMapEntry");
      this.ld = this.A.getType("HandlerType");
      this.gp = this.A.getType("UnwindInfo");
      this.oT = this.A.getType("UnwindCode");
      this.fI = this.A.getType("ScopeTableEntry");
      this.WR = this.A.getType("RUNTIME_FUNCTION");
      this.NS = this.A.getType("_EH3_SCOPETABLE_ENTRY");
      this.vP = this.A.getType("_EH4_SCOPETABLE_ENTRY");
      this.xC = this.A.getType("_EH4_SCOPETABLE");
      this.ED = chj.pC(this.A, var1.getProcessor().getMode() == 64);
   }
}
