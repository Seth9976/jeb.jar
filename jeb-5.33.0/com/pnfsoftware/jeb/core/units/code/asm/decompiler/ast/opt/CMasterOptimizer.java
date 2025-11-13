package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.AbstractMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.acj;
import com.pnfsoftware.jebglobal.aco;
import com.pnfsoftware.jebglobal.agm;
import com.pnfsoftware.jebglobal.ago;
import com.pnfsoftware.jebglobal.agp;
import com.pnfsoftware.jebglobal.agq;
import com.pnfsoftware.jebglobal.agr;
import com.pnfsoftware.jebglobal.ags;
import com.pnfsoftware.jebglobal.agt;
import com.pnfsoftware.jebglobal.agu;
import com.pnfsoftware.jebglobal.agv;
import com.pnfsoftware.jebglobal.agw;
import com.pnfsoftware.jebglobal.agy;
import com.pnfsoftware.jebglobal.agz;
import com.pnfsoftware.jebglobal.aha;
import com.pnfsoftware.jebglobal.ahb;
import com.pnfsoftware.jebglobal.ahc;
import com.pnfsoftware.jebglobal.ahd;
import com.pnfsoftware.jebglobal.ahe;
import com.pnfsoftware.jebglobal.ahf;
import com.pnfsoftware.jebglobal.ahg;
import com.pnfsoftware.jebglobal.ahh;
import com.pnfsoftware.jebglobal.ahi;
import com.pnfsoftware.jebglobal.ahj;
import com.pnfsoftware.jebglobal.ahk;
import com.pnfsoftware.jebglobal.ahl;
import com.pnfsoftware.jebglobal.aho;
import com.pnfsoftware.jebglobal.ahp;
import com.pnfsoftware.jebglobal.ahq;
import com.pnfsoftware.jebglobal.ahr;
import com.pnfsoftware.jebglobal.ahs;
import com.pnfsoftware.jebglobal.aht;
import com.pnfsoftware.jebglobal.ahu;
import com.pnfsoftware.jebglobal.ahv;
import com.pnfsoftware.jebglobal.ahw;
import com.pnfsoftware.jebglobal.ahy;
import com.pnfsoftware.jebglobal.ahz;
import com.pnfsoftware.jebglobal.aia;
import com.pnfsoftware.jebglobal.aib;
import com.pnfsoftware.jebglobal.aic;
import com.pnfsoftware.jebglobal.aid;
import com.pnfsoftware.jebglobal.aie;
import com.pnfsoftware.jebglobal.aif;
import com.pnfsoftware.jebglobal.aii;
import com.pnfsoftware.jebglobal.aij;
import com.pnfsoftware.jebglobal.aik;
import com.pnfsoftware.jebglobal.ail;
import com.pnfsoftware.jebglobal.ain;
import com.pnfsoftware.jebglobal.aio;
import com.pnfsoftware.jebglobal.aip;
import com.pnfsoftware.jebglobal.aiq;
import com.pnfsoftware.jebglobal.air;
import com.pnfsoftware.jebglobal.ais;
import com.pnfsoftware.jebglobal.ait;

public class CMasterOptimizer extends AbstractMasterOptimizer implements ICMasterOptimizer {
   private static final StructuredLogger logger = aco.pC(CMasterOptimizer.class);
   public static final CMasterOptimizer EMPTY = new CMasterOptimizer();
   public static int defaultMaxRunCount = -1;

   private CMasterOptimizer() {
      this(null, 1);
   }

   public CMasterOptimizer(ICMethod var1) {
      this(var1, -1);
   }

   public CMasterOptimizer(ICMethod var1, int var2) {
      super(var1, var2);
      this.registerOptimizer(1, new ahu());
      this.registerOptimizer(1, new ahz());
      this.registerOptimizer(1, new ahg());
      this.registerOptimizer(1, new ahk());
      this.registerOptimizer(1, new aij());
      this.registerOptimizer(1, new ahe());
      this.registerOptimizer(1, new ais());
      this.registerOptimizer(1, new ait());
      this.registerOptimizer(1, new agu());
      this.registerOptimizer(1, new agv());
      this.registerOptimizer(1, new agt());
      this.registerOptimizer(1, new agy());
      this.registerOptimizer(1, new agz());
      this.registerOptimizer(1, new agw());
      this.registerOptimizer(1, new ahv());
      this.registerOptimizer(1, new ahr());
      this.registerOptimizer(1, new ahs());
      this.registerOptimizer(1, new ahh());
      this.registerOptimizer(1, new aip());
      this.registerOptimizer(1, new aho());
      this.registerOptimizer(1, new ahy());
      this.registerOptimizer(1, new ago());
      this.registerOptimizer(1, new aio());
      this.registerOptimizer(2, new agm());
      this.registerOptimizer(2, new ain());
      this.registerOptimizer(2, new ahj());
      this.registerOptimizer(2, new aia());
      this.registerOptimizer(2, new ahq());
      this.registerOptimizer(2, new aii());
      this.registerOptimizer(2, new aif());
      this.registerOptimizer(2, new aid());
      this.registerOptimizer(2, new aik());
      this.registerOptimizer(2, new aie());
      this.registerOptimizer(2, new ail());
      this.registerOptimizer(2, new air());
      this.registerOptimizer(2, new aiq());
      this.registerOptimizer(2, new ahl());
      this.registerOptimizer(2, new aha());
      this.registerOptimizer(2, new ahp());
      this.registerOptimizer(3, new ags());
      this.registerOptimizer(3, new ahd());
      this.registerOptimizer(3, new ahc());
      this.registerOptimizer(3, new ahb());
      this.registerOptimizer(3, new ahi());
      this.registerOptimizer(3, new ahf());
      this.registerOptimizer(-1, new aic());
      this.registerOptimizer(-1, new aib());
      this.registerOptimizer(-1, new agr());
      this.registerOptimizer(-1, new agp());
      this.registerOptimizer(-1, new agq());
      this.registerOptimizer(-1, new ahw());
      this.registerOptimizer(-1, new aht());
   }

   protected void postOptimizationCallback(ICMethod var1, OptimizerEntry var2, int var3, long var4) {
      super.postOptimizationCallback(var1, var2, var3, var4);
   }

   protected void postAllOptimizationsCallback(ICMethod var1) {
      super.postAllOptimizationsCallback(var1);
   }

   protected boolean onOptimizerException(ICMethod var1, IOptimizer var2, Exception var3) {
      boolean var4 = super.onOptimizerException(var1, var2, var3);
      acj.pC(var3, var1);
      return var4;
   }

   protected String getTargetAddress(ICMethod var1) {
      return var1.getAddress();
   }
}
