package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Wk {
   private static final ILogger q = GlobalLog.getLogger(Wk.class);
   private Ux RF;
   private Object xK = new Object();
   private int Dw = 0;
   private Map Uv = Collections.synchronizedMap(new LinkedHashMap());

   Wk(Ux var1) {
      this.RF = var1;
   }

   public QF q() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 1, null);
      Hv var2 = this.RF.q(var1);
      return QF.q(var2);
   }

   public Uc RF() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 4, null);
      Hv var2 = this.RF.q(var1);
      return Uc.q(var2);
   }

   public BW xK() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 5, null);
      Hv var2 = this.RF.q(var1);
      return BW.q(var2);
   }

   void Dw() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 6, null);
      this.RF.q(var1);
   }

   public nG Uv() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 7, null);
      Hv var2 = this.RF.q(var1);
      return nG.q(var2);
   }

   public void oW() throws IOException, Fx {
      synchronized (this.xK) {
         YZ var2 = this.RF.q(1, 8, null);
         this.RF.q(var2);
         this.Dw++;
      }
   }

   public void gO() throws IOException, Fx {
      synchronized (this.xK) {
         YZ var2 = this.RF.q(1, 9, null);
         this.RF.q(var2);
         this.Dw--;
      }
   }

   void q(int var1) throws IOException, Fx {
      byte[] var2 = this.RF.LK().q(var1).q();
      YZ var3 = this.RF.q(1, 10, var2);
      this.RF.q(var3);
   }

   public long q(String var1) throws IOException, Fx {
      byte[] var2 = this.RF.LK().q(var1).q();
      YZ var3 = this.RF.q(1, 11, var2);
      Hv var4 = this.RF.q(var3);
      return var4.gO();
   }

   XP nf() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 12, null);
      Hv var2 = this.RF.q(var1);
      return XP.q(var2);
   }

   Oo gP() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 17, null);
      Hv var2 = this.RF.q(var1);
      return Oo.q(var2);
   }

   public Gl za() throws IOException, Fx {
      YZ var1 = this.RF.q(1, 20, null);
      Hv var2 = this.RF.q(var1);
      return Gl.q(var2);
   }

   public List q(long var1, List var3) throws IOException, Fx {
      wc var4 = this.RF.LK().gP(var1).q(var3.size());

      for (long var6 : var3) {
         var4.HF(var6);
      }

      byte[] var11 = var4.q();
      YZ var12 = this.RF.q(2, 6, var11);
      Hv var7 = this.RF.q(var12);
      int var8 = var7.Uv();
      ArrayList var9 = new ArrayList(var8);

      for (int var10 = 0; var10 < var8; var10++) {
         var9.add(var7.KT());
      }

      return var9;
   }

   public ch q(long var1, long var3) throws IOException, Fx {
      return (ch)this.q(var1, Lists.createArrayList(var3)).get(0);
   }

   @Deprecated
   List q(long var1) throws IOException, Fx {
      wc var3 = this.RF.LK().gP(var1);
      byte[] var4 = var3.q();
      YZ var5 = this.RF.q(2, 8, var4);
      Hv var6 = this.RF.q(var5);
      int var7 = var6.Uv();
      ArrayList var8 = new ArrayList(var7);

      for (int var9 = 0; var9 < var7; var9++) {
         var6.RF();
         var8.add(var6.HF());
      }

      return var8;
   }

   List RF(long var1) throws IOException, Fx {
      wc var3 = this.RF.LK().gP(var1);
      byte[] var4 = var3.q();
      YZ var5 = this.RF.q(2, 10, var4);
      Hv var6 = this.RF.q(var5);
      int var7 = var6.Uv();
      ArrayList var8 = new ArrayList(var7);

      for (int var9 = 0; var9 < var7; var9++) {
         var8.add(var6.HF());
      }

      return var8;
   }

   Vi xK(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().gP(var1).q();
      YZ var4 = this.RF.q(2, 13, var3);
      Hv var5 = this.RF.q(var4);
      return Vi.q(var5);
   }

   QB Dw(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().gP(var1).q();
      YZ var4 = this.RF.q(2, 14, var3);
      Hv var5 = this.RF.q(var4);
      return QB.q(var5);
   }

   wM Uv(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().gP(var1).q();
      YZ var4 = this.RF.q(2, 15, var3);
      Hv var5 = this.RF.q(var4);
      return wM.q(var5);
   }

   long oW(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().gP(var1).q();
      YZ var4 = this.RF.q(3, 1, var3);
      Hv var5 = this.RF.q(var4);
      return var5.LK();
   }

   public boolean q(long var1, List var3, List var4) throws IOException, Fx {
      if (var3.size() != var4.size()) {
         return false;
      } else {
         wc var5 = this.RF.LK().za(var1).q(var3.size());

         for (int var6 = 0; var6 < var3.size(); var6++) {
            var5.HF((Long)var3.get(var6)).RF((ch)var4.get(var6));
         }

         byte[] var8 = var5.q();
         YZ var7 = this.RF.q(3, 2, var8);
         this.RF.q(var7);
         return true;
      }
   }

   public boolean q(long var1, Long var3, ch var4) throws IOException, Fx {
      return this.q(var1, Arrays.asList(var3), Arrays.asList(var4));
   }

   ch q(long var1, long var3, long var5, List var7) throws IOException, Fx {
      wc var8 = this.RF.LK().za(var1).xK(var3).JY(var5).q(var7.size());

      for (ch var10 : var7) {
         var8.q(var10);
      }

      var8.q(1);
      byte[] var14 = var8.q();
      YZ var15 = this.RF.q(3, 3, var14);
      Hv var11 = this.RF.q(var15);
      ch var12 = var11.KT();
      ch var13 = var11.KT();
      return var13.RF == 0L ? var12 : var13;
   }

   ch RF(long var1, long var3, long var5, List var7) throws IOException, Fx {
      wc var8 = this.RF.LK().za(var1).xK(var3).JY(var5).q(var7.size());

      for (ch var10 : var7) {
         var8.q(var10);
      }

      var8.q(1);
      byte[] var14 = var8.q();
      YZ var15 = this.RF.q(3, 4, var14);
      Hv var11 = this.RF.q(var15);
      ch var12 = var11.KT();
      ch var13 = var11.KT();
      return var13.RF == 0L ? var12 : var13;
   }

   public long q(long var1, int var3) throws IOException, Fx {
      byte[] var4 = this.RF.LK().gP(var1).q(var3).q();
      YZ var5 = this.RF.q(4, 1, var4);
      Hv var6 = this.RF.q(var5);
      var6.RF();
      return var6.gO();
   }

   public gg gO(long var1) throws IOException, Fx {
      if (var1 == 0L) {
         return new gg((byte)1, 0L);
      } else {
         byte[] var3 = this.RF.LK().RF(var1).q();
         YZ var4 = this.RF.q(9, 1, var3);
         Hv var5 = this.RF.q(var4);
         byte var6 = var5.RF();
         long var7 = var5.HF();
         return new gg(var6, var7);
      }
   }

   public List RF(long var1, List var3) throws IOException, Fx {
      if (var1 == 0L) {
         return null;
      } else {
         wc var4 = this.RF.LK().RF(var1).q(var3.size());

         for (long var6 : var3) {
            var4.HF(var6);
         }

         byte[] var11 = var4.q();
         YZ var12 = this.RF.q(9, 2, var11);
         Hv var7 = this.RF.q(var12);
         int var8 = var7.Uv();
         ArrayList var9 = new ArrayList();

         for (int var10 = 0; var10 < var8; var10++) {
            var9.add(var7.KT());
         }

         return var9;
      }
   }

   public List RF(long var1, long var3) throws IOException, Fx {
      return this.RF(var1, Arrays.asList(var3));
   }

   public boolean RF(long var1, List var3, List var4) throws IOException, Fx {
      if (var1 != 0L && var3.size() == var4.size()) {
         wc var5 = this.RF.LK().RF(var1).q(var3.size());

         for (int var6 = 0; var6 < var3.size(); var6++) {
            var5.HF((Long)var3.get(var6)).RF((ch)var4.get(var6));
         }

         byte[] var8 = var5.q();
         YZ var7 = this.RF.q(9, 3, var8);
         this.RF.q(var7);
         return true;
      } else {
         return false;
      }
   }

   public boolean RF(long var1, Long var3, ch var4) throws IOException, Fx {
      return this.RF(var1, Arrays.asList(var3), Arrays.asList(var4));
   }

   public Ki nf(long var1) throws IOException, Fx {
      if (var1 == 0L) {
         return null;
      } else {
         byte[] var3 = this.RF.LK().RF(var1).q();
         YZ var4 = this.RF.q(9, 5, var3);
         Hv var5 = this.RF.q(var4);
         return Ki.q(var5);
      }
   }

   public ch q(long var1, long var3, long var5, long var7, List var9) throws IOException, Fx {
      if (var1 == 0L) {
         throw new IllegalArgumentException("Can not invoke method on null");
      } else {
         wc var10 = this.RF.LK().RF(var1).xK(var3).za(var5).JY(var7).q(var9.size());

         for (ch var12 : var9) {
            var10.q(var12);
         }

         var10.q(1);
         byte[] var15 = var10.q();
         YZ var16 = this.RF.q(9, 6, var15);
         Hv var13 = this.RF.q(var16);
         return var13.KT();
      }
   }

   public String gP(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().RF(var1).q();
      YZ var4 = this.RF.q(10, 1, var3);
      Hv var5 = this.RF.q(var4);
      return var5.xW();
   }

   public String za(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().xK(var1).q();
      YZ var4 = this.RF.q(11, 1, var3);
      Hv var5 = this.RF.q(var4);
      return var5.xW();
   }

   public void lm(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().xK(var1).q();
      YZ var4 = this.RF.q(11, 2, var3);
      this.RF.q(var4);
   }

   public void zz(long var1) throws IOException, Fx {
      this.q(var1, true);
   }

   public void q(long var1, boolean var3) throws IOException, Fx {
      byte[] var4 = this.RF.LK().xK(var1).q();
      YZ var5 = this.RF.q(11, 3, var4);
      if (var3) {
         this.RF.q(var5);
      } else {
         this.RF.RF(var5.RF);
      }
   }

   public TG JY(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().xK(var1).q();
      YZ var4 = this.RF.q(11, 4, var3);
      Hv var5 = this.RF.q(var4);
      return TG.q(var5);
   }

   public long HF(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().xK(var1).q();
      YZ var4 = this.RF.q(11, 5, var3);
      Hv var5 = this.RF.q(var4);
      return var5.gP();
   }

   public NB q(long var1, int var3, int var4) throws IOException, Fx {
      byte[] var5 = this.RF.LK().xK(var1).q(var3).q(var4).q();
      YZ var6 = this.RF.q(11, 6, var5);
      Hv var7 = this.RF.q(var6);
      return NB.q(var7);
   }

   public NB LK(long var1) throws IOException, Fx {
      return this.q(var1, 0, -1);
   }

   public int io(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().xK(var1).q();
      YZ var4 = this.RF.q(11, 7, var3);
      Hv var5 = this.RF.q(var4);
      return var5.Uv();
   }

   public int qa(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().xK(var1).q();
      YZ var4 = this.RF.q(11, 12, var3);
      Hv var5 = this.RF.q(var4);
      return var5.Uv();
   }

   public String Hk(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().Dw(var1).q();
      YZ var4 = this.RF.q(12, 1, var3);
      Hv var5 = this.RF.q(var4);
      return var5.xW();
   }

   public long Me(long var1) throws IOException, Fx {
      byte[] var3 = this.RF.LK().Dw(var1).q();
      YZ var4 = this.RF.q(12, 2, var3);
      Hv var5 = this.RF.q(var4);
      return var5.gP();
   }

   public int PV(long var1) throws IOException, Fx {
      if (var1 == 0L) {
         return 0;
      } else {
         byte[] var3 = this.RF.LK().RF(var1).q();
         YZ var4 = this.RF.q(13, 1, var3);
         Hv var5 = this.RF.q(var4);
         return var5.Uv();
      }
   }

   public ch[] RF(long var1, int var3, int var4) throws IOException, Fx {
      if (var1 == 0L) {
         return null;
      } else {
         byte[] var5 = this.RF.LK().RF(var1).q(var3).q(var4).q();
         YZ var6 = this.RF.q(13, 2, var5);
         Hv var7 = this.RF.q(var6);
         return var7.Gf();
      }
   }

   public boolean q(long var1, int var3, List var4) throws IOException, Fx {
      if (var1 != 0L && var4.size() != 0) {
         wc var5 = this.RF.LK().RF(var1).q(var3).q(var4.size());

         for (int var6 = 0; var6 < var4.size(); var6++) {
            var5.RF((ch)var4.get(var6));
         }

         byte[] var8 = var5.q();
         YZ var7 = this.RF.q(13, 3, var8);
         this.RF.q(var7);
         return true;
      } else {
         return false;
      }
   }

   public boolean q(long var1, int var3, ch var4) throws IOException, Fx {
      return this.q(var1, var3, Arrays.asList(var4));
   }

   public ch[] q(long var1, long var3, hf[] var5) throws IOException, Fx {
      wc var6 = this.RF.LK().xK(var1).LK(var3).q(var5.length);

      for (hf var10 : var5) {
         var6.q(var10.q);
         var6.q(var10.RF);
      }

      byte[] var13 = var6.q();
      YZ var14 = this.RF.q(16, 1, var13);
      Hv var15 = this.RF.q(var14);
      int var16 = var15.Uv();
      ch[] var11 = new ch[var16];

      for (int var12 = 0; var12 < var16; var12++) {
         var11[var12] = var15.KT();
      }

      return var11;
   }

   public List q(long var1, long var3, List var5) throws IOException, Fx {
      ch[] var6 = this.q(var1, var3, (hf[])var5.toArray(new hf[var5.size()]));
      return Arrays.asList(var6);
   }

   public ch q(long var1, long var3, hf var5) throws IOException, Fx {
      hf[] var6 = new hf[]{var5};
      return this.q(var1, var3, var6)[0];
   }

   public boolean q(long var1, long var3, hf[] var5, ch[] var6) throws IOException, Fx {
      if (var5.length != var6.length) {
         return false;
      } else {
         wc var7 = this.RF.LK().xK(var1).LK(var3).q(var5.length);

         for (int var8 = 0; var8 < var5.length; var8++) {
            var7.q(var5[var8].q).q(var6[var8]);
         }

         byte[] var10 = var7.q();
         YZ var9 = this.RF.q(16, 2, var10);
         this.RF.q(var9);
         return true;
      }
   }

   public boolean q(long var1, long var3, hf var5, ch var6) throws IOException, Fx {
      return this.q(var1, var3, new hf[]{var5}, new ch[]{var6});
   }

   public VO xK(long var1, long var3) throws IOException, Fx {
      wc var5 = this.RF.LK().xK(var1).LK(var3);
      byte[] var6 = var5.q();
      YZ var7 = this.RF.q(16, 3, var6);
      Hv var8 = this.RF.q(var7);
      return VO.q(var8);
   }

   public rg oQ(long var1) throws IOException, Fx {
      wc var3 = this.RF.LK().gO(var1);
      byte[] var4 = var3.q();
      YZ var5 = this.RF.q(17, 1, var4);
      Hv var6 = this.RF.q(var5);
      return rg.q(var6);
   }

   int q(dN var1, m var2, to var3) throws IOException, Fx {
      if (var3 == null) {
         var3 = new to();
      }

      wc var4 = this.RF.LK().q(var1.q()).q(var2.q()).q(var3.q());

      for (Vm var6 : var3) {
         var4.q(var6.q);
         if (var6.q == 1) {
            var4.q(var6.RF);
         } else if (var6.q == 2) {
            var4.q(var6.xK);
         } else if (var6.q == 3) {
            var4.xK(var6.Dw);
         } else if (var6.q == 4) {
            var4.gP(var6.Uv);
         } else if (var6.q == 5) {
            var4.q(var6.oW);
         } else if (var6.q == 6) {
            var4.q(var6.oW);
         } else if (var6.q == 7) {
            var4.q(var6.gO);
         } else if (var6.q == 8) {
            var4.gP(var6.Uv);
            var4.q(var6.nf);
            var4.q(var6.gP);
         } else if (var6.q == 9) {
            var4.gP(var6.Uv);
            var4.HF(var6.za);
         } else if (var6.q == 10) {
            var4.xK(var6.Dw);
            var4.q(var6.lm);
            var4.q(var6.zz);
         } else if (var6.q == 11) {
            var4.RF(var6.JY);
         } else {
            if (var6.q != 12) {
               throw new si("Unsupported modifier kind for event: " + var6.q);
            }

            var4.q(var6.HF);
         }
      }

      byte[] var9 = var4.q();
      YZ var10 = this.RF.q(15, 1, var9);
      Hv var7 = this.RF.q(var10);
      int var8 = var7.Uv();
      this.Uv.put(var8, var1);
      return var8;
   }

   void q(dN var1, int var2) throws IOException, Fx {
      dN var3 = (dN)this.Uv.get(var2);
      if (var3 == null) {
         q.warn("Request ID %d is not tracked, a JDWP error might occur", var2);
      } else if (var3 != var1) {
         q.warn("Request ID %d has an event kind %s (not %s), a JDWP error might occur", var3, var1);
      }

      byte[] var4 = this.RF.LK().q(var1.q()).q(var2).q();
      YZ var5 = this.RF.q(15, 2, var4);
      this.RF.q(var5);
      this.Uv.remove(var2);
   }

   public dN RF(int var1) {
      return (dN)this.Uv.get(var1);
   }

   public boolean xK(int var1) {
      return this.Uv.containsKey(var1);
   }

   void lm() throws IOException, Fx {
      synchronized (this.Uv) {
         while (!this.Uv.isEmpty()) {
            int var2 = (Integer)this.Uv.keySet().iterator().next();
            this.q((dN)this.Uv.get(var2), var2);
            this.Uv.remove(var2);
         }
      }
   }

   Hv q(int var1, int var2, byte[] var3) throws IOException, Fx {
      YZ var4 = this.RF.q(var1, var2, var3);
      this.RF.RF(var4.RF);
      return this.RF.q(var4);
   }

   int RF(int var1, int var2, byte[] var3) throws IOException {
      YZ var4 = this.RF.q(var1, var2, var3);
      this.RF.RF(var4.RF);
      return var4.q;
   }
}
