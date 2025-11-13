package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabelFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class adh implements ICGlobalContext {
   @SerId(1)
   public adi pC;
   @SerId(2)
   public afk A;
   @SerId(3)
   public aeq kS;
   @SerId(4)
   public adw wS;
   @SerId(5)
   public afb UT;
   @SerId(6)
   public adb E;
   @SerId(7)
   public adf sY;
   @SerId(8)
   public aej ys;
   @SerId(9)
   IWildcardTypeManager ld;
   @SerId(10)
   public ICNamingEngine gp;

   public adh(IWildcardTypeManager var1) {
      this.ld = var1;
      this.A = new afk(var1.create("void"), var1.create("int"), var1.create("unsigned int"), var1.create("char"));
      this.kS = new aeq(null);
      this.wS = new adw(this.A);
      this.UT = new afb(this.A);
      this.pC = new adi(this);
      this.E = new adb(this);
      this.sY = new adf(this);
      this.ys = new aej(this);
   }

   @Override
   public ICIdentifierManager createLocalIdentifierManager() {
      return new aeq(this.getNamingEngine());
   }

   @Override
   public ICLabelFactory createLabelFactory() {
      return new aev();
   }

   public ICNamingEngine pC(ICNamingEngine var1) {
      ICNamingEngine var2 = this.gp;
      this.kS.setNamingEngine(var1);
      this.gp = var1;
      return var2;
   }

   @Override
   public ICNamingEngine getNamingEngine() {
      return this.gp;
   }

   public IWildcardTypeManager pC() {
      return this.ld;
   }

   public adb A() {
      return this.E;
   }

   public adf kS() {
      return this.sY;
   }

   public adi wS() {
      return this.pC;
   }

   public afk UT() {
      return this.A;
   }

   public aeq E() {
      return this.kS;
   }

   public adw sY() {
      return this.wS;
   }

   public afb ys() {
      return this.UT;
   }

   public aej ld() {
      return this.ys;
   }
}
