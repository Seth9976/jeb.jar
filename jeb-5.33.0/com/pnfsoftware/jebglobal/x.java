package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class x {
   private static final ILogger pC = GlobalLog.getLogger(x.class);
   private bA A;
   private Object kS = new Object();
   private int wS = 0;
   private Map UT = Collections.synchronizedMap(new LinkedHashMap());

   x(bA a) {
      this.A = a;
   }

   public Zg pC() throws IOException, oY {
      return Zg.pC(this.A.pC(this.A.pC(1, 1, null)));
   }

   public Mw A() throws IOException, oY {
      return Mw.pC(this.A.pC(this.A.pC(1, 4, null)));
   }

   void kS() throws IOException, oY {
      this.A.pC(this.A.pC(1, 6, null));
   }

   public xi wS() throws IOException, oY {
      return xi.pC(this.A.pC(this.A.pC(1, 7, null)));
   }

   public void UT() throws IOException, oY {
      synchronized (this.kS) {
         this.A.pC(this.A.pC(1, 8, null));
         this.wS++;
      }
   }

   public void E() throws IOException, oY {
      synchronized (this.kS) {
         this.A.pC(this.A.pC(1, 9, null));
         this.wS--;
      }
   }

   void pC(int n) throws IOException, oY {
      this.A.pC(this.A.pC(1, 10, this.A.WR().pC(n).pC()));
   }

   public long pC(String s) throws IOException, oY {
      return this.A.pC(this.A.pC(1, 11, this.A.WR().pC(s).pC())).E();
   }

   kJ sY() throws IOException, oY {
      return kJ.pC(this.A.pC(this.A.pC(1, 17, null)));
   }

   public im ys() throws IOException, oY {
      return im.pC(this.A.pC(this.A.pC(1, 20, null)));
   }

   public List pC(long n, List list) throws IOException, oY {
      VD pc = this.A.WR().wS(n).pC(list.size());
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         pc.sY((Long)iterator.next());
      }

      AN pc2 = this.A.pC(this.A.pC(2, 6, pc.pC()));
      int ws = pc2.wS();
      ArrayList list2 = new ArrayList(ws);

      for (int i = 0; i < ws; i++) {
         list2.add(pc2.vP());
      }

      return list2;
   }

   public rG pC(long n, long l) throws IOException, oY {
      return (rG)this.pC(n, Lists.createArrayList(l)).get(0);
   }

   List pC(long n) throws IOException, oY {
      AN pc = this.A.pC(this.A.pC(2, 10, this.A.WR().wS(n).pC()));
      int ws = pc.wS();
      ArrayList list = new ArrayList(ws);

      for (int i = 0; i < ws; i++) {
         list.add(pc.ys());
      }

      return list;
   }

   JV A(long n) throws IOException, oY {
      return JV.pC(this.A.pC(this.A.pC(2, 13, this.A.WR().wS(n).pC())));
   }

   qk kS(long n) throws IOException, oY {
      return qk.pC(this.A.pC(this.A.pC(2, 14, this.A.WR().wS(n).pC())));
   }

   jG wS(long n) throws IOException, oY {
      return jG.pC(this.A.pC(this.A.pC(2, 15, this.A.WR().wS(n).pC())));
   }

   long UT(long n) throws IOException, oY {
      return this.A.pC(this.A.pC(3, 1, this.A.WR().wS(n).pC())).ld();
   }

   public boolean pC(long n, List list, List list2) throws IOException, oY {
      if (list.size() != list2.size()) {
         return false;
      } else {
         VD pc = this.A.WR().UT(n).pC(list.size());

         for (int i = 0; i < list.size(); i++) {
            pc.sY((Long)list.get(i)).A((rG)list2.get(i));
         }

         this.A.pC(this.A.pC(3, 2, pc.pC()));
         return true;
      }
   }

   public boolean pC(long n, Long n2, rG rg) throws IOException, oY {
      return this.pC(n, Arrays.asList(n2), Arrays.asList(rg));
   }

   rG pC(long n, long n2, long n3, List list) throws IOException, oY {
      VD pc = this.A.WR().UT(n).kS(n2).E(n3).pC(list.size());
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         pc.pC((rG)iterator.next());
      }

      pc.pC(1);
      AN pc2 = this.A.pC(this.A.pC(3, 3, pc.pC()));
      rG vp = pc2.vP();
      rG vp2 = pc2.vP();
      return vp2.A == 0L ? vp : vp2;
   }

   rG A(long n, long n2, long n3, List list) throws IOException, oY {
      VD pc = this.A.WR().UT(n).kS(n2).E(n3).pC(list.size());
      Iterator iterator = list.iterator();

      while (iterator.hasNext()) {
         pc.pC((rG)iterator.next());
      }

      pc.pC(1);
      AN pc2 = this.A.pC(this.A.pC(3, 4, pc.pC()));
      rG vp = pc2.vP();
      rG vp2 = pc2.vP();
      return vp2.A == 0L ? vp : vp2;
   }

   public long pC(long n, int n2) throws IOException, oY {
      AN pc = this.A.pC(this.A.pC(4, 1, this.A.WR().wS(n).pC(n2).pC()));
      pc.pC();
      return pc.E();
   }

   public sK E(long n) throws IOException, oY {
      if (n == 0L) {
         return new sK((byte)1, 0L);
      } else {
         AN pc = this.A.pC(this.A.pC(9, 1, this.A.WR().A(n).pC()));
         return new sK(pc.pC(), pc.ys());
      }
   }

   public List A(long n, List list) throws IOException, oY {
      if (n == 0L) {
         return null;
      } else {
         VD pc = this.A.WR().A(n).pC(list.size());
         Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            pc.sY((Long)iterator.next());
         }

         AN pc2 = this.A.pC(this.A.pC(9, 2, pc.pC()));
         int ws = pc2.wS();
         ArrayList list2 = new ArrayList();

         for (int i = 0; i < ws; i++) {
            list2.add(pc2.vP());
         }

         return list2;
      }
   }

   public List gy(long n, long l) throws IOException, oY {
      return this.A(n, Arrays.asList(l));
   }

   public boolean A(long n, List list, List list2) throws IOException, oY {
      if (n != 0L && list.size() == list2.size()) {
         VD pc = this.A.WR().A(n).pC(list.size());

         for (int i = 0; i < list.size(); i++) {
            pc.sY((Long)list.get(i)).A((rG)list2.get(i));
         }

         this.A.pC(this.A.pC(9, 3, pc.pC()));
         return true;
      } else {
         return false;
      }
   }

   public boolean A(long n, Long n2, rG rg) throws IOException, oY {
      return this.A(n, Arrays.asList(n2), Arrays.asList(rg));
   }

   public rG pC(long n, long n2, long n3, long n4, List list) throws IOException, oY {
      if (n == 0L) {
         throw new IllegalArgumentException("Can not invoke method on null");
      } else {
         VD pc = this.A.WR().A(n).kS(n2).UT(n3).E(n4).pC(list.size());
         Iterator iterator = list.iterator();

         while (iterator.hasNext()) {
            pc.pC((rG)iterator.next());
         }

         pc.pC(1);
         return this.A.pC(this.A.pC(9, 6, pc.pC())).vP();
      }
   }

   public String sY(long n) throws IOException, oY {
      return this.A.pC(this.A.pC(10, 1, this.A.WR().A(n).pC())).NS();
   }

   public String ys(long n) throws IOException, oY {
      return this.A.pC(this.A.pC(11, 1, this.A.WR().kS(n).pC())).NS();
   }

   public void ld(long n) throws IOException, oY {
      this.A.pC(this.A.pC(11, 2, this.A.WR().kS(n).pC()));
   }

   public void gp(long n) throws IOException, oY {
      this.pC(n, true);
   }

   public void pC(long n, boolean b) throws IOException, oY {
      Yy pc = this.A.pC(11, 3, this.A.WR().kS(n).pC());
      if (b) {
         this.A.pC(pc);
      } else {
         this.A.A(pc.A);
      }
   }

   public lS oT(long n) throws IOException, oY {
      return lS.pC(this.A.pC(this.A.pC(11, 4, this.A.WR().kS(n).pC())));
   }

   public jZ pC(long n, int n2, int n3) throws IOException, oY {
      return jZ.pC(this.A.pC(this.A.pC(11, 6, this.A.WR().kS(n).pC(n2).pC(n3).pC())));
   }

   public int fI(long n) throws IOException, oY {
      return this.A.pC(this.A.pC(11, 7, this.A.WR().kS(n).pC())).wS();
   }

   public int WR(long n) throws IOException, oY {
      return this.A.pC(this.A.pC(11, 12, this.A.WR().kS(n).pC())).wS();
   }

   public int NS(long n) throws IOException, oY {
      return n == 0L ? 0 : this.A.pC(this.A.pC(13, 1, this.A.WR().A(n).pC())).wS();
   }

   public rG[] A(long n, int n2, int n3) throws IOException, oY {
      return n == 0L ? null : this.A.pC(this.A.pC(13, 2, this.A.WR().A(n).pC(n2).pC(n3).pC())).xC();
   }

   public boolean pC(long n, int n2, List list) throws IOException, oY {
      if (n != 0L && list.size() != 0) {
         VD pc = this.A.WR().A(n).pC(n2).pC(list.size());

         for (int i = 0; i < list.size(); i++) {
            pc.A((rG)list.get(i));
         }

         this.A.pC(this.A.pC(13, 3, pc.pC()));
         return true;
      } else {
         return false;
      }
   }

   public boolean pC(long n, int n2, rG rg) throws IOException, oY {
      return this.pC(n, n2, Arrays.asList(rg));
   }

   public rG[] pC(long n, long n2, eL[] array) throws IOException, oY {
      VD pc = this.A.WR().kS(n).ys(n2).pC(array.length);

      for (eL el : array) {
         pc.pC(el.pC);
         pc.pC(el.A);
      }

      AN pc2 = this.A.pC(this.A.pC(16, 1, pc.pC()));
      int ws = pc2.wS();
      rG[] array2 = new rG[ws];

      for (int j = 0; j < ws; j++) {
         array2[j] = pc2.vP();
      }

      return array2;
   }

   public rG pC(long n, long n2, eL el) throws IOException, oY {
      return this.pC(n, n2, new eL[]{el})[0];
   }

   public boolean pC(long n, long n2, eL[] array, rG[] array2) throws IOException, oY {
      if (array.length != array2.length) {
         return false;
      } else {
         VD pc = this.A.WR().kS(n).ys(n2).pC(array.length);

         for (int i = 0; i < array.length; i++) {
            pc.pC(array[i].pC).pC(array2[i]);
         }

         this.A.pC(this.A.pC(16, 2, pc.pC()));
         return true;
      }
   }

   public boolean pC(long n, long n2, eL el, rG rg) throws IOException, oY {
      return this.pC(n, n2, new eL[]{el}, new rG[]{rg});
   }

   public uG A(long n, long n2) throws IOException, oY {
      return uG.pC(this.A.pC(this.A.pC(16, 3, this.A.WR().kS(n).ys(n2).pC())));
   }

   int pC(lz lz, Jh jh, Fz fz) throws IOException, oY {
      if (fz == null) {
         fz = new Fz();
      }

      VD pc = this.A.WR().pC(lz.pC()).pC(jh.pC()).pC(fz.pC());

      for (AM am : fz) {
         pc.pC(am.pC);
         if (am.pC == 1) {
            pc.pC(am.A);
         } else if (am.pC == 2) {
            pc.pC(am.kS);
         } else if (am.pC == 3) {
            pc.kS(am.wS);
         } else if (am.pC == 4) {
            pc.wS(am.UT);
         } else if (am.pC == 5) {
            pc.pC(am.E);
         } else if (am.pC == 6) {
            pc.pC(am.E);
         } else if (am.pC == 7) {
            pc.pC(am.sY);
         } else if (am.pC == 8) {
            pc.wS(am.UT);
            pc.pC(am.ys);
            pc.pC(am.ld);
         } else if (am.pC == 9) {
            pc.wS(am.UT);
            pc.sY(am.gp);
         } else if (am.pC == 10) {
            pc.kS(am.wS);
            pc.pC(am.oT);
            pc.pC(am.fI);
         } else if (am.pC == 11) {
            pc.A(am.WR);
         } else {
            if (am.pC != 12) {
               throw new yb("Unsupported modifier kind for event: " + am.pC);
            }

            pc.pC(am.NS);
         }
      }

      int ws = this.A.pC(this.A.pC(15, 1, pc.pC())).wS();
      this.UT.put(ws, lz);
      return ws;
   }

   void pC(lz lz, int i) throws IOException, oY {
      lz lz2 = (lz)this.UT.get(i);
      if (lz2 == null) {
         pC.warn("Request ID %d is not tracked, a JDWP error might occur", i);
      } else if (lz2 != lz) {
         pC.warn("Request ID %d has an event kind %s (not %s), a JDWP error might occur", lz2, lz);
      }

      this.A.pC(this.A.pC(15, 2, this.A.WR().pC(lz.pC()).pC(i).pC()));
      this.UT.remove(i);
   }

   public lz A(int i) {
      return (lz)this.UT.get(i);
   }
}
