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

public class cdb implements AutoCloseable2 {
   private static final ILogger pC = GlobalLog.getLogger(cdb.class);
   private static final boolean A = atd.pC();
   private Context kS = new Context();
   private ccz wS = new ccz();
   private StringBuilder UT = new StringBuilder();
   private int E;
   private String sY;
   private String ys;

   public static boolean pC() {
      return A;
   }

   public void pC(int var1) {
      this.E = var1;
   }

   @Override
   public void close() {
      if (this.kS == null) {
         throw new IllegalStateException("Already closed");
      } else {
         this.kS.close();
         this.kS = null;
         this.wS = null;
      }
   }

   public void pC(IDExpression var1) throws asz {
      if (this.sY != null) {
         throw new IllegalStateException();
      } else {
         this.UT.append(var1);
         this.wS.pC(var1);
      }
   }

   private BoolExpr wS() throws Z3Exception {
      this.E();
      return this.pC(this.sY);
   }

   private BoolExpr UT() throws Z3Exception {
      this.E();
      return this.pC(this.ys);
   }

   private void E() throws Z3Exception {
      if (this.sY == null) {
         String[] var1 = this.wS.pC();
         this.sY = var1[0];
         this.ys = var1[1];
      }
   }

   private BoolExpr pC(String var1) throws Z3Exception {
      BoolExpr[] var2 = this.kS.parseSMTLIB2String(var1, null, null, null, null);
      return var2[0];
   }

   public Boolean A() {
      return this.pC(this.wS());
   }

   public Boolean kS() {
      return this.pC(this.UT());
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private Boolean pC(BoolExpr... var1) {
      Solver var2 = this.kS.mkSolver();
      var2.add(var1);
      if (this.E >= 0) {
         Params var3 = this.kS.mkParams();
         var3.add("timeout", this.E);
         var2.setParameters(var3);
      }

      Status var4 = var2.check();

      return switch (var4) {
         case SATISFIABLE -> true;
         case UNSATISFIABLE -> false;
         default -> null;
      };
   }

   public static boolean A(IDExpression var0) {
      return var0.visitDepthPost(new cdc());
   }
}
