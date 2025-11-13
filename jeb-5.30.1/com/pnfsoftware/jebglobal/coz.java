package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.wincommon.Winunp;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class coz {
   private static final ILogger oQ = GlobalLog.getLogger(coz.class);
   int q;
   int RF;
   int xK;
   int Dw;
   int Uv;
   int oW;
   int gO;
   int nf;
   int gP;
   int za;
   int lm;
   int zz;
   int JY;
   int HF;
   int LK;
   int io;
   int qa;
   int Hk;
   int Me;
   List PV = new ArrayList();

   public static coz q(ByteBuffer var0) {
      coz var1 = new coz();
      var1.q = var0.getInt();
      var1.RF = var0.getInt();
      var1.xK = var0.getInt();
      var1.Dw = var0.getShort() & '\uffff';
      var1.Uv = var0.getShort() & '\uffff';
      var1.oW = var0.getShort() & '\uffff';
      var1.gO = var0.getShort() & '\uffff';
      var1.nf = var0.getShort() & '\uffff';
      var1.gP = var0.getShort() & '\uffff';
      var1.za = var0.getInt();
      var1.lm = var0.getInt();
      var1.zz = var0.getInt();
      var1.JY = var0.getInt();
      var1.HF = var0.getInt();
      var1.LK = var0.getInt();
      var1.io = var0.getInt();
      var1.qa = var0.getInt();
      var1.Hk = var0.getShort() & '\uffff';
      var1.Me = var0.getShort() & '\uffff';
      var0.getInt();
      if (var1.q != -1) {
         throw new cpe("Unexpected DBI stream header");
      } else {
         int var2 = var0.position() + var1.za;

         while (var0.position() < var2) {
            coz.eo var3 = coz.eo.q(var0);
            var1.PV.add(var3);
         }

         return var1;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("DbiStream");
   }

   static class CU {
      int q;
      int RF;
      int xK;
      int Dw;
      int Uv;
      int oW;
      int gO;

      static coz.CU q(ByteBuffer var0) {
         coz.CU var1 = new coz.CU();
         var1.q = var0.getShort() & '\uffff';
         var0.getShort();
         var1.RF = var0.getInt();
         var1.xK = var0.getInt();
         var1.Dw = var0.getInt();
         var1.Uv = var0.getShort() & '\uffff';
         var0.getShort();
         var1.oW = var0.getInt();
         var1.gO = var0.getInt();
         return var1;
      }

      @Override
      public String toString() {
         return Strings.ff("DBISectionContribution:%X:o=%X,s=%X", this.q, this.RF, this.xK);
      }
   }

   static class eo {
      int q;
      coz.CU RF;
      int xK;
      int Dw;
      int Uv;
      int oW;
      int gO;
      int nf;
      int gP;
      int za;
      int lm;
      byte[] zz;
      byte[] JY;

      static coz.eo q(ByteBuffer var0) {
         coz.eo var1 = new coz.eo();
         var1.q = var0.getInt();
         var1.RF = coz.CU.q(var0);
         var1.xK = var0.getShort() & '\uffff';
         var1.Dw = var0.getShort() & '\uffff';
         var1.Uv = var0.getInt();
         var1.oW = var0.getInt();
         var1.gO = var0.getInt();
         var1.nf = var0.getShort() & '\uffff';
         var0.getShort();
         var1.gP = var0.getInt();
         var1.za = var0.getInt();
         var1.lm = var0.getInt();
         var1.zz = Winunp.parseCString(var0);
         var1.JY = Winunp.parseCString(var0);
         ByteBufferUtil.align(var0, 4);
         return var1;
      }

      @Override
      public String toString() {
         return Strings.ff(
            "opened=%X,section=%s,stream=%X,module_name=%s,object_file_name=%s",
            this.q,
            this.RF,
            this.Dw,
            Strings.decodeASCII(this.zz),
            Strings.decodeASCII(this.JY)
         );
      }
   }
}
