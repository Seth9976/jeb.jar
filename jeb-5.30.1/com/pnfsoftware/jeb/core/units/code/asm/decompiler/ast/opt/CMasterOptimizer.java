package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.AbstractMasterOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.IOptimizer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.opt.OptimizerEntry;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeb;
import com.pnfsoftware.jebglobal.aeg;
import com.pnfsoftware.jebglobal.aih;
import com.pnfsoftware.jebglobal.aij;
import com.pnfsoftware.jebglobal.aik;
import com.pnfsoftware.jebglobal.ail;
import com.pnfsoftware.jebglobal.aim;
import com.pnfsoftware.jebglobal.aio;
import com.pnfsoftware.jebglobal.aip;
import com.pnfsoftware.jebglobal.aiq;
import com.pnfsoftware.jebglobal.air;
import com.pnfsoftware.jebglobal.ais;
import com.pnfsoftware.jebglobal.aiu;
import com.pnfsoftware.jebglobal.aiv;
import com.pnfsoftware.jebglobal.aiw;
import com.pnfsoftware.jebglobal.aix;
import com.pnfsoftware.jebglobal.aiy;
import com.pnfsoftware.jebglobal.aiz;
import com.pnfsoftware.jebglobal.aja;
import com.pnfsoftware.jebglobal.ajb;
import com.pnfsoftware.jebglobal.ajc;
import com.pnfsoftware.jebglobal.ajd;
import com.pnfsoftware.jebglobal.ajf;
import com.pnfsoftware.jebglobal.ajg;
import com.pnfsoftware.jebglobal.aji;
import com.pnfsoftware.jebglobal.ajj;
import com.pnfsoftware.jebglobal.ajm;
import com.pnfsoftware.jebglobal.ajn;
import com.pnfsoftware.jebglobal.ajo;
import com.pnfsoftware.jebglobal.ajp;
import com.pnfsoftware.jebglobal.ajq;
import com.pnfsoftware.jebglobal.ajr;
import com.pnfsoftware.jebglobal.ajs;
import com.pnfsoftware.jebglobal.ajt;
import com.pnfsoftware.jebglobal.aju;
import com.pnfsoftware.jebglobal.ajw;
import com.pnfsoftware.jebglobal.ajy;
import com.pnfsoftware.jebglobal.ajz;
import com.pnfsoftware.jebglobal.aka;
import com.pnfsoftware.jebglobal.akb;
import com.pnfsoftware.jebglobal.akc;
import com.pnfsoftware.jebglobal.akd;
import com.pnfsoftware.jebglobal.ake;
import com.pnfsoftware.jebglobal.akh;
import com.pnfsoftware.jebglobal.aki;
import com.pnfsoftware.jebglobal.akj;
import com.pnfsoftware.jebglobal.akk;
import com.pnfsoftware.jebglobal.akm;
import com.pnfsoftware.jebglobal.akn;
import com.pnfsoftware.jebglobal.ako;
import com.pnfsoftware.jebglobal.akp;
import com.pnfsoftware.jebglobal.akq;
import com.pnfsoftware.jebglobal.akr;
import com.pnfsoftware.jebglobal.aks;

public class CMasterOptimizer extends AbstractMasterOptimizer implements ICMasterOptimizer {
   private static final StructuredLogger logger = aeg.q(CMasterOptimizer.class);
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
      this.registerOptimizer(1, new ajs());
      this.registerOptimizer(1, new ajy());
      this.registerOptimizer(1, new ajc());
      this.registerOptimizer(1, new aji());
      this.registerOptimizer(1, new aki());
      this.registerOptimizer(1, new aja());
      this.registerOptimizer(1, new akr());
      this.registerOptimizer(1, new aks());
      this.registerOptimizer(1, new aiq());
      this.registerOptimizer(1, new air());
      this.registerOptimizer(1, new aip());
      this.registerOptimizer(1, new aiu());
      this.registerOptimizer(1, new aiv());
      this.registerOptimizer(1, new ais());
      this.registerOptimizer(1, new ajt());
      this.registerOptimizer(1, new ajp());
      this.registerOptimizer(1, new ajq());
      this.registerOptimizer(1, new ajd());
      this.registerOptimizer(1, new ako());
      this.registerOptimizer(1, new ajm());
      this.registerOptimizer(1, new ajw());
      this.registerOptimizer(1, new aij());
      this.registerOptimizer(1, new akn());
      this.registerOptimizer(2, new aih());
      this.registerOptimizer(2, new akm());
      this.registerOptimizer(2, new ajg());
      this.registerOptimizer(2, new ajz());
      this.registerOptimizer(2, new ajo());
      this.registerOptimizer(2, new akh());
      this.registerOptimizer(2, new ake());
      this.registerOptimizer(2, new akc());
      this.registerOptimizer(2, new akj());
      this.registerOptimizer(2, new akd());
      this.registerOptimizer(2, new akk());
      this.registerOptimizer(2, new akq());
      this.registerOptimizer(2, new akp());
      this.registerOptimizer(2, new ajj());
      this.registerOptimizer(2, new aiw());
      this.registerOptimizer(2, new ajn());
      this.registerOptimizer(3, new aio());
      this.registerOptimizer(3, new aiz());
      this.registerOptimizer(3, new aiy());
      this.registerOptimizer(3, new aix());
      this.registerOptimizer(3, new ajf());
      this.registerOptimizer(3, new ajb());
      this.registerOptimizer(-1, new akb());
      this.registerOptimizer(-1, new aka());
      this.registerOptimizer(-1, new aim());
      this.registerOptimizer(-1, new aik());
      this.registerOptimizer(-1, new ail());
      this.registerOptimizer(-1, new aju());
      this.registerOptimizer(-1, new ajr());
   }

   protected void postOptimizationCallback(ICMethod var1, OptimizerEntry var2, int var3, long var4) {
      super.postOptimizationCallback(var1, var2, var3, var4);
   }

   protected void postAllOptimizationsCallback(ICMethod var1) {
      super.postAllOptimizationsCallback(var1);
   }

   protected boolean onOptimizerException(ICMethod var1, IOptimizer var2, Exception var3) {
      boolean var4 = super.onOptimizerException(var1, var2, var3);
      aeb.q(var3, var1);
      return var4;
   }

   protected String getTargetAddress(ICMethod var1) {
      return var1.getAddress();
   }
}
