package com.pnfsoftware.jebglobal;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.FuncDecl;
import com.microsoft.z3.Model;
import com.microsoft.z3.Params;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;
import com.microsoft.z3.Z3Exception;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.AutoCloseable2;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.HashMap;

public class avt implements AutoCloseable2, avs {
   private static final StructuredLogger q = aeg.q(avt.class);
   private static final boolean RF = avv.q();
   private Context xK;
   private avq Dw;
   private StringBuilder Uv = new StringBuilder();
   private boolean oW = false;
   private int gO = 0;
   private int nf;

   public static boolean RF() {
      return RF;
   }

   public avt() {
      this.xK = new Context();
      this.Dw = new avq();
   }

   public avt(avt var1) {
      this.xK = new Context();
      this.Dw = new avq(var1.Dw());
      this.gO = var1.oW();
   }

   public void q(int var1) {
      this.nf = var1;
   }

   public int xK() {
      return this.nf;
   }

   @Override
   public void close() {
      if (this.xK == null) {
         throw new IllegalStateException("Already closed");
      } else {
         this.xK.close();
         this.xK = null;
         this.Dw = null;
      }
   }

   public avq Dw() {
      return this.Dw;
   }

   public void Uv() {
      this.oW = true;
   }

   @Override
   public boolean q(IEGeneric var1) {
      this.Uv.append(var1);
      if (var1 instanceof IEStatement && !(var1 instanceof IEAssign) && !this.oW) {
         Object[] var2 = new Object[]{var1};
         return false;
      } else {
         if (this.oW && var1 instanceof IEReturn) {
            if (((IEReturn)var1).getValue() == null) {
               Object[] var10000 = new Object[0];
               return false;
            }

            var1 = ((IEReturn)var1).getValue();
         }

         if (!EUtil.isLogicalOperation((IEGeneric)var1) && !(var1 instanceof IEAssign)) {
            var1 = EUtil.ne((IEGeneric)var1, EUtil.zero(((IEGeneric)var1).getBitsize()));
         }

         this.Dw.q((IEGeneric)var1);
         this.gO++;
         return true;
      }
   }

   public int oW() {
      return this.gO;
   }

   public BoolExpr gO() throws Z3Exception {
      String var1 = this.Dw.xK();
      return this.q(var1);
   }

   public BoolExpr q(String var1) throws Z3Exception {
      try {
         BoolExpr[] var2 = this.xK.parseSMTLIB2String(var1, null, null, null, null);
         return var2[0];
      } catch (Z3Exception var4) {
         q.catching(var4);
         Object[] var10000 = new Object[]{var1};
         HashMap var3 = Maps.toMap("formula", var1);
         var3.put("ir-expression", this.Uv.toString());
         JebCoreService.notifySilentExceptionToClient(var4, var3);
         throw var4;
      }
   }

   public void nf() {
      Model var1 = this.gP();
      if (var1 != null) {
         Object[] var10000 = new Object[]{var1.toString()};
      }
   }

   public Model gP() {
      Solver var1 = this.xK.mkSolver();
      var1.add(new Expr[]{this.gO()});
      if (var1.check() == Status.SATISFIABLE) {
         return var1.getModel();
      } else {
         Object[] var10000 = new Object[0];
         return null;
      }
   }

   public Expr q(IEVar var1) {
      Model var2 = this.gP();
      String var3 = this.Dw().RF(var1);

      for (FuncDecl var7 : var2.getConstDecls()) {
         if (var7.getName().toString().equals(var3)) {
            return var2.getConstInterp(var7);
         }
      }

      return null;
   }

   @Override
   public Boolean q() {
      Solver var1 = this.xK.mkSolver();
      var1.add(new Expr[]{this.gO()});
      if (this.nf >= 0) {
         Params var2 = this.xK.mkParams();
         var2.add("timeout", this.nf);
         var1.setParameters(var2);
      }

      Status var3 = var1.check();
      switch (var3) {
         case SATISFIABLE:
            return true;
         case UNSATISFIABLE:
            return false;
         default:
            return null;
      }
   }
}
