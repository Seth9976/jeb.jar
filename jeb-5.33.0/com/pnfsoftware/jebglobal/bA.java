package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.Version;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerThreadStatus;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class bA extends wn implements Ha {
   private static final ILogger oT = GlobalLog.getLogger(bA.class);
   private static final byte[] fI = Strings.encodeUTF8("JDWP-Handshake");
   private String WR;
   private int NS;
   private boolean vP;
   private Version xC;
   private int ED;
   private Ay Sc;
   private OutputStream ah;
   private List eP = new ArrayList();
   private qZ UO;
   private AtomicInteger Ab = new AtomicInteger(1);
   private Object rl = new Object();
   private Object z = new Object();
   private Map Ek = new ConcurrentHashMap();
   vE A;
   kJ kS;
   x wS;
   y UT;
   TK E;
   fK sY;
   WU ys;
   private Jh hK = Jh.A;
   Map ld = Collections.synchronizedMap(new HashMap());
   long gp = 0L;

   public bA(String var1, int var2, boolean var3) {
      this.WR = var1;
      this.NS = var2;
      this.vP = var3;
   }

   @Override
   public void pC(int var1) {
      this.ED = var1;
   }

   @Override
   public boolean A() throws wX {
      this.A = new vE(4, 4, 8, 8, 8);

      try {
         this.pC = new Socket(this.WR, this.NS);
         this.ah = this.pC.getOutputStream();
         this.ah.write(fI);
         byte[] var1 = new byte[14];
         int var2 = this.pC.getInputStream().read(var1);
         if (var2 == fI.length && Arrays.equals(var1, fI)) {
            this.Sc = new Ay(this);
            this.Sc.setDaemon(true);
            this.Sc.start();
            this.UO = new qZ(this);
            this.UO.setDaemon(true);
            this.UO.start();
            this.wS = new x(this);
            this.UT = new y(this);
            this.E = new TK(this);
            this.sY = new fK(this);
            this.ys = new WU(this);
            if (this.vP) {
               this.wS.UT();
            }

            Zg var11 = this.wS.pC();
            this.xC = Version.create(var11.A, var11.kS);
            xi var4 = this.wS.wS();
            this.A = new vE(var4.pC, var4.A, var4.kS, var4.wS, var4.UT);

            try {
               this.kS = this.gp().sY();
               Object[] var10000 = new Object[]{this.kS};
            } catch (oY var7) {
               oT.error("Cannot retrieve JDWP server server capabilities");
            }

            this.oT().A(true);
            this.oT().kS(true);
            this.oT().wS(true);
            return true;
         } else {
            String var3 = Strings.ff("Unexpected JDWP handshake: %s", Formatter.escapeBytes(var1, 0, var2));
            var3 = var3
               + "\n\nVerify that the Developer Mode and USB Debugging are enabled on your device.\n\nIf Android Studio is opened, please close it, or enable ADB integration in 'Tools>Android'. We also recommend you close DDMS or other monitoring tools that can interfere with app debugging.\n\nIf it still does not work, try disconnecting and reconnecting your device, or try with another device.";
            throw new wX(var3);
         }
      } catch (oY | IOException var8) {
         oT.catching(var8);
         if (this.pC != null) {
            try {
               this.pC.close();
            } catch (IOException var6) {
            }

            this.pC = null;
         }

         return false;
      } catch (wX var9) {
         if (this.pC != null) {
            try {
               this.pC.close();
            } catch (IOException var5) {
            }

            this.pC = null;
         }

         throw var9;
      }
   }

   qZ ld() {
      return this.UO;
   }

   public x gp() {
      return this.wS;
   }

   public y oT() {
      return this.UT;
   }

   public TK fI() {
      return this.E;
   }

   @Override
   public boolean pC(boolean var1) {
      if (!this.pC()) {
         return false;
      } else {
         try {
            if (this.wS != null) {
               if (var1) {
                  this.wS.pC(-1);
               } else {
                  this.wS.kS();
               }
            }
         } catch (oY | IOException var6) {
            oT.catching(var6);
         }

         try {
            this.pC.close();
         } catch (IOException var5) {
            oT.catching(var5);
         }

         this.pC = null;
         if (this.Sc != null) {
            this.Sc.interrupt();

            try {
               this.Sc.join();
            } catch (InterruptedException var4) {
               oT.catching(var4);
            }
         }

         if (this.UO != null) {
            this.UO.interrupt();

            try {
               this.UO.join();
            } catch (InterruptedException var3) {
               oT.catching(var3);
            }
         }

         return true;
      }
   }

   @Override
   public Version E() {
      return this.xC;
   }

   @Override
   public boolean kS() {
      return this.UT.kS();
   }

   @Override
   public boolean wS() {
      return this.UT.wS();
   }

   @Override
   public boolean UT() {
      return this.UT.UT();
   }

   AN pC(byte[] var1) {
      return this.A.pC(var1);
   }

   VD WR() {
      return this.A.pC();
   }

   void A(byte[] var1) throws IOException {
      synchronized (this.rl) {
         this.ah.write(var1);
      }
   }

   @Override
   public void pC(xl var1) {
      this.eP.add(var1);
   }

   @Override
   public void A(xl var1) {
      this.eP.remove(var1);
   }

   List NS() {
      return this.eP;
   }

   void pC(BG var1) {
      if (var1.A()) {
         synchronized (this.z) {
            this.Ek.put(var1.kS(), var1);
            this.z.notify();
         }
      } else if (var1.pC()) {
         byte var5 = var1.wS();
         byte var3 = var1.UT();
         if (var5 == 64 && var3 == 100) {
            this.UO.pC(var1);
         } else if (var5 == -57 && var3 == 1) {
            this.UO.pC(var1);
         } else {
            oT.error("Unknown JDWP command packet: %s", var1);
         }
      }
   }

   private int Sc() {
      return this.Ab.getAndIncrement();
   }

   Yy pC(int var1, int var2, byte[] var3) {
      int var4 = this.Sc();
      byte[] var5 = BG.pC(var4, (byte)0, (byte)var1, (byte)var2, var3);
      return new Yy(var4, var5);
   }

   private BG A(int var1) throws IOException, oY {
      BG var2;
      synchronized (this.z) {
         int var4 = 0;

         while (!this.Ek.containsKey(var1)) {
            try {
               if (var4++ > 0) {
                  if (var4 == 1) {
                     Object[] var8 = new Object[0];
                  }

                  if (!this.Sc.isAlive()) {
                     throw new IOException("The receiver is dead");
                  }
               }

               this.z.wait(1000L);
               if (this.ED >= 1 && var4 >= this.ED) {
                  throw new IOException(Strings.ff("A JDWP debugger seems blocked: a synchronous query is not receiving a response (id: %d)", var1));
               }
            } catch (InterruptedException var6) {
            }
         }

         var2 = (BG)this.Ek.get(var1);
      }

      if (var2.E() != 0) {
         throw new oY(var2.kS(), var2.E());
      } else {
         return var2;
      }
   }

   AN pC(Yy var1) throws IOException, oY {
      this.A(var1.A);
      BG var2 = this.A(var1.pC);
      return this.pC(var2.sY());
   }

   public Jh vP() {
      return this.hK;
   }

   @Override
   public String sY() {
      return this.UT.A();
   }

   @Override
   public List ys() {
      return this.UT.E();
   }

   @Override
   public boolean pC(long var1) {
      return this.UT.UT(var1);
   }

   @Override
   public boolean A(long var1) {
      return this.UT.pC(var1, true);
   }

   @Override
   public boolean kS(long var1) {
      return this.UT.pC(var1, false);
   }

   @Override
   public String wS(long var1) {
      return this.UT.wS(var1);
   }

   @Override
   public DebuggerThreadStatus UT(long var1) {
      return this.UT.E(var1);
   }

   @Override
   public List pC(long var1, int var3) {
      try {
         if (var3 <= 0) {
            var3 = -1;
         }

         jZ var4 = this.wS.pC(var1, 0, var3);
         return Arrays.asList(var4.pC);
      } catch (oY | IOException var5) {
         oT.catching(var5);
         return null;
      }
   }

   @Override
   public String E(long var1) {
      try {
         return this.UT.kS(var1);
      } catch (wX | IOException var4) {
         oT.catching(var4);
         return null;
      }
   }

   @Override
   public List pC(String var1) {
      try {
         return this.UT.pC(var1);
      } catch (wX | IOException var3) {
         oT.catching(var3);
         return null;
      }
   }

   @Override
   public String pC(long var1, long var3) {
      try {
         return this.UT.pC(var1, var3);
      } catch (wX | IOException var6) {
         oT.catching(var6);
         return null;
      }
   }

   @Override
   public boolean pC(PZ var1, int var2) {
      return this.sY.pC(var1, var2);
   }

   @Override
   public boolean pC(PZ var1) {
      return this.sY.pC(var1);
   }

   @Override
   public Jx sY(long var1) {
      try {
         SL var3 = this.wS.pC(var1, 0, 1).pC[0];
         return var3.A;
      } catch (oY | IOException var4) {
         return null;
      }
   }

   @Override
   public boolean A(long var1, int var3) {
      try {
         if (var3 == 2) {
            synchronized (this.ld) {
               LP var5 = (LP)this.ld.get(var1);
               if (var5 != null) {
                  return false;
               }

               this.ld.put(var1, null);
            }
         }

         ArrayList var9 = new ArrayList();
         AM var10 = AM.pC(var1, 0, var3);
         var9.add(var10);
         var9.add(AM.A("com.sun.*"));
         var9.add(AM.A("java.*"));
         var9.add(AM.A("javax.*"));
         var9.add(AM.A("org.omg.*"));
         var9.add(AM.A("sun.*"));
         var9.add(AM.A("jdk.internal.*"));
         var9.add(AM.A("junit.*"));
         var9.add(AM.A("com.intellij.rt.*"));
         var9.add(AM.A("com.yourkit.runtime.*"));
         var9.add(AM.A("com.springsource.loaded.*"));
         var9.add(AM.A("org.springsource.loaded.*"));
         var9.add(AM.A("javassist.*"));
         var9.add(AM.A("org.apache.webbeans.*"));
         var9.add(AM.A("com.ibm.ws.*"));
         var9.add(AM.A("org.codehaus.groovy.*"));
         var9.add(AM.A("groovy.*"));
         LP var6 = this.UT.pC(lz.pC, this.vP(), new Fz((AM[])var9.toArray(new AM[var9.size()])));
         if (var3 == 2) {
            Object[] var10000 = new Object[]{var6};
            this.ld.put(var1, var6);
         }

         return true;
      } catch (oY | IOException var8) {
         return false;
      }
   }

   public void ys(long var1) {
      this.gp = var1;
   }

   public boolean kS(long var1, int var3) {
      if (var1 == 0L) {
         var1 = this.gp;
         if (var1 == 0L) {
            return false;
         }
      }

      if (this.UT(var1) != DebuggerThreadStatus.PAUSED) {
         return false;
      } else {
         return !this.A(var1, var3) ? false : this.kS(var1);
      }
   }

   public boolean xC() {
      return this.kS(this.gp, 0);
   }

   public boolean ED() {
      return this.kS(this.gp, 1);
   }

   @Override
   public String toString() {
      return Strings.ff("JDWP{%s:%d}", this.WR, this.NS);
   }
}
