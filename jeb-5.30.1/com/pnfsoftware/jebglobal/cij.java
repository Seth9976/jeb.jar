package com.pnfsoftware.jebglobal;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Params;
import com.microsoft.z3.Solver;
import com.microsoft.z3.Status;
import com.microsoft.z3.Z3Exception;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.util.base.AutoCloseable2;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class cij implements AutoCloseable2 {
   private static final ILogger q = GlobalLog.getLogger(cij.class);
   private static final boolean RF = avv.q();
   private Context xK = new Context();
   private cie Dw;
   private StringBuilder Uv;
   private int oW;
   private String gO;
   private String nf;

   public static boolean q() {
      return RF;
   }

   public cij() {
      this.Dw = new cie();
      this.Uv = new StringBuilder();
   }

   public cij(cij var1) {
      this.Dw = new cie(var1.Dw);
      this.Uv = new StringBuilder(var1.Uv);
      this.oW = var1.oW;
   }

   public void q(int var1) {
      this.oW = var1;
   }

   public int RF() {
      return this.oW;
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

   public cie xK() {
      return this.Dw;
   }

   public void q(IDExpression var1) throws cig {
      if (this.gO != null) {
         throw new IllegalStateException();
      } else {
         this.Uv.append(var1);
         this.Dw.q(var1);
      }
   }

   private BoolExpr oW() throws Z3Exception {
      this.nf();
      return this.q(this.gO);
   }

   private BoolExpr gO() throws Z3Exception {
      this.nf();
      return this.q(this.nf);
   }

   private void nf() throws Z3Exception {
      if (this.gO == null) {
         String[] var1 = this.Dw.xK();
         this.gO = var1[0];
         this.nf = var1[1];
      }
   }

   private BoolExpr q(String var1) throws Z3Exception {
      BoolExpr[] var2 = this.xK.parseSMTLIB2String(var1, null, null, null, null);
      return var2[0];
   }

   public Boolean Dw() {
      return this.q(this.oW());
   }

   public Boolean Uv() {
      return this.q(this.gO());
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private Boolean q(BoolExpr... var1) {
      Solver var2 = this.xK.mkSolver();
      var2.add(var1);
      if (this.oW >= 0) {
         Params var3 = this.xK.mkParams();
         var3.add("timeout", this.oW);
         var2.setParameters(var3);
      }

      Status var4 = var2.check();
      switch (var4) {
         case SATISFIABLE:
            return true;
         case UNSATISFIABLE:
            return false;
         default:
            return null;
      }
   }

   public static boolean RF(IDExpression var0) {
      return var0.visitDepthPost(new cik());
   }
}
