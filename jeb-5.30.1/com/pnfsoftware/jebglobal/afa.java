package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabelFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class afa implements ICGlobalContext {
   @SerId(1)
   public afb q;
   @SerId(2)
   public ahd RF;
   @SerId(3)
   public agj xK;
   @SerId(4)
   public afp Dw;
   @SerId(5)
   public agu Uv;
   @SerId(6)
   public aeu oW;
   @SerId(7)
   public aey gO;
   @SerId(8)
   public agc nf;
   @SerId(9)
   IWildcardTypeManager gP;
   @SerId(10)
   public ICNamingEngine za;

   public afa(IWildcardTypeManager var1) {
      this.gP = var1;
      this.RF = new ahd(var1.create("void"), var1.create("int"), var1.create("unsigned int"), var1.create("char"));
      this.xK = new agj(null);
      this.Dw = new afp(this.RF);
      this.Uv = new agu(this.RF);
      this.q = new afb(this);
      this.oW = new aeu(this);
      this.gO = new aey(this);
      this.nf = new agc(this);
   }

   @Override
   public ICIdentifierManager createLocalIdentifierManager() {
      return new agj(this.getNamingEngine());
   }

   @Override
   public ICLabelFactory createLabelFactory() {
      return new ago();
   }

   public ICNamingEngine q(ICNamingEngine var1) {
      ICNamingEngine var2 = this.za;
      this.xK.setNamingEngine(var1);
      this.za = var1;
      return var2;
   }

   @Override
   public ICNamingEngine getNamingEngine() {
      return this.za;
   }

   public IWildcardTypeManager q() {
      return this.gP;
   }

   public aeu RF() {
      return this.oW;
   }

   public aey xK() {
      return this.gO;
   }

   public afb Dw() {
      return this.q;
   }

   public ahd Uv() {
      return this.RF;
   }

   public agj oW() {
      return this.xK;
   }

   public afp gO() {
      return this.Dw;
   }

   public agu nf() {
      return this.Uv;
   }

   public agc gP() {
      return this.nf;
   }
}
