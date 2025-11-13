package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class chv {
   private static final ILogger UO = GlobalLog.getLogger(chv.class);
   int pC;
   int A;
   int kS;
   int wS;
   int UT;
   int E;
   int sY;
   int ys;
   int ld;
   int gp;
   int oT;
   int fI;
   int WR;
   int NS;
   int vP;
   int xC;
   int ED;
   int Sc;
   int ah;
   List eP = new ArrayList();

   public static chv pC(ByteBuffer var0) {
      chv var1 = new chv();
      var1.pC = var0.getInt();
      var1.A = var0.getInt();
      var1.kS = var0.getInt();
      var1.wS = var0.getShort() & '\uffff';
      var1.UT = var0.getShort() & '\uffff';
      var1.E = var0.getShort() & '\uffff';
      var1.sY = var0.getShort() & '\uffff';
      var1.ys = var0.getShort() & '\uffff';
      var1.ld = var0.getShort() & '\uffff';
      var1.gp = var0.getInt();
      var1.oT = var0.getInt();
      var1.fI = var0.getInt();
      var1.WR = var0.getInt();
      var1.NS = var0.getInt();
      var1.vP = var0.getInt();
      var1.xC = var0.getInt();
      var1.ED = var0.getInt();
      var1.Sc = var0.getShort() & '\uffff';
      var1.ah = var0.getShort() & '\uffff';
      var0.getInt();
      if (var1.pC != -1) {
         throw new chy("Unexpected DBI stream header");
      } else {
         int var2 = var0.position() + var1.gp;

         while (var0.position() < var2) {
            chv.Av var3 = chv.Av.pC(var0);
            var1.eP.add(var3);
         }

         return var1;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("DbiStream");
   }

   static class Av {
      int pC;
      chv.Sv A;
      int kS;
      int wS;
      int UT;
      int E;
      int sY;
      int ys;
      int ld;
      int gp;
      int oT;
      byte[] fI;
      byte[] WR;

      static chv.Av pC(ByteBuffer var0) {
         chv.Av var1 = new chv.Av();
         var1.pC = var0.getInt();
         var1.A = chv.Sv.pC(var0);
         var1.kS = var0.getShort() & '\uffff';
         var1.wS = var0.getShort() & '\uffff';
         var1.UT = var0.getInt();
         var1.E = var0.getInt();
         var1.sY = var0.getInt();
         var1.ys = var0.getShort() & '\uffff';
         var0.getShort();
         var1.ld = var0.getInt();
         var1.gp = var0.getInt();
         var1.oT = var0.getInt();
         var1.fI = Winunp.parseCString(var0);
         var1.WR = Winunp.parseCString(var0);
         ByteBufferUtil.align(var0, 4);
         return var1;
      }

      @Override
      public String toString() {
         return Strings.ff(
            "opened=%X,section=%s,stream=%X,module_name=%s,object_file_name=%s",
            this.pC,
            this.A,
            this.wS,
            Strings.decodeASCII(this.fI),
            Strings.decodeASCII(this.WR)
         );
      }
   }

   static class Sv {
      int pC;
      int A;
      int kS;
      int wS;
      int UT;
      int E;
      int sY;

      static chv.Sv pC(ByteBuffer var0) {
         chv.Sv var1 = new chv.Sv();
         var1.pC = var0.getShort() & '\uffff';
         var0.getShort();
         var1.A = var0.getInt();
         var1.kS = var0.getInt();
         var1.wS = var0.getInt();
         var1.UT = var0.getShort() & '\uffff';
         var0.getShort();
         var1.E = var0.getInt();
         var1.sY = var0.getInt();
         return var1;
      }

      @Override
      public String toString() {
         return Strings.ff("DBISectionContribution:%X:o=%X,s=%X", this.pC, this.A, this.kS);
      }
   }
}
