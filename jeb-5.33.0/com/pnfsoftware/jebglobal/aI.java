package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVirtualMemory;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorUtil;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class aI extends wn implements XV {
   private static final ILogger Sc = GlobalLog.getLogger(aI.class);
   boolean A = true;
   private String ah;
   private int eP;
   private jT UO;
   private boolean Ab;
   private ProcessorType rl = ProcessorType.UNKNOWN;
   private Endianness z = Endianness.LITTLE_ENDIAN;
   private int Ek;
   private List hK;
   private IZ Er;
   private it FE;
   private OutputStream Aj;
   private Object EX = new Object();
   private List LM = new ArrayList();
   private Object mv = new Object();
   private Queue sO = new ConcurrentLinkedQueue();
   private hq os;
   YC kS;
   private Map Cu = new TreeMap();
   private int hZ;
   Object wS = new Object();
   volatile boolean UT;
   private boolean UW;
   private Gg PR;
   qa E;
   rr sY;
   XE ys;
   OL ld;
   NK gp;
   xO oT;
   Q fI;
   KM WR;
   Ti NS;
   AS vP;
   boolean xC;
   private AtomicInteger cX = new AtomicInteger();
   private static final byte[] DQ = Strings.encodeASCII("vCont;");
   Map ED = Collections.synchronizedMap(new HashMap());

   public aI(String var1, int var2) {
      this(var1, var2, null);
   }

   public aI(String var1, int var2, jT var3) {
      if (var1 == null) {
         var1 = "localhost";
      }

      this.ah = var1;
      this.eP = var2;
      if (var3 == null) {
         var3 = jT.pC;
      }

      this.UO = var3;
   }

   public jT E() {
      return this.UO;
   }

   public void pC(ProcessorType var1) {
      if (this.Ab) {
         throw new IllegalStateException("The debugger is already connected to a target");
      } else {
         this.rl = var1;
      }
   }

   public ProcessorType sY() {
      return this.rl;
   }

   public void pC(Endianness var1) {
      if (this.Ab) {
         throw new IllegalStateException("The debugger is already connected to a target");
      } else {
         this.z = var1;
      }
   }

   public Endianness ys() {
      return this.z;
   }

   public void pC(int var1) {
      this.Ek = var1;
   }

   public void pC(String var1) {
      ArrayList var2 = null;
      if (!Strings.isBlank(var1)) {
         var2 = new ArrayList();

         for (String var6 : var1.split(",")) {
            var6 = var6.trim();

            try {
               int var7 = var6.indexOf(45);
               if (var7 >= 0) {
                  int var13 = Integer.parseInt(var6.substring(0, var7).trim());
                  int var9 = Integer.parseInt(var6.substring(var7 + 1).trim());

                  for (int var10 = var13; var10 <= var9; var10++) {
                     var2.add(var10);
                  }
               } else {
                  int var8 = Integer.parseInt(var6);
                  var2.add(var8);
               }
            } catch (Exception var11) {
            }
         }
      }

      this.pC(var2);
   }

   public void pC(List var1) {
      this.hK = var1;
   }

   public void pC(Gg var1) {
      this.PR = var1;
   }

   public Gg ld() {
      return this.PR;
   }

   public synchronized boolean gp() {
      if (this.Ab) {
         throw new IllegalStateException("The debugger is already connected to a target");
      } else {
         try {
            this.pC = new Socket(this.ah, this.eP);
            this.Aj = this.pC.getOutputStream();
            this.Er = new IZ(this);
            this.Er.setDaemon(true);
            this.Er.start();
            this.FE = new it(this);
            this.FE.setDaemon(true);
            this.FE.start();
            this.DQ();
            this.E = new qa(this);
            this.sY = new rr(this);
            this.ys = new XE(this, this.sY().is64Bit() ? 64 : 32, 4096);
            this.oT = new xO(this);
            this.ld = new OL(this);
            if (this.sY() == ProcessorType.ARM) {
               this.gp = new tJ(this);
            } else if (this.sY() == ProcessorType.ARM64) {
               this.gp = new Is(this);
            } else if (this.sY() == ProcessorType.X86) {
               this.gp = new GE(this);
            } else if (this.sY() == ProcessorType.X86_64) {
               this.gp = new CM(this);
            } else {
               if (this.sY() != ProcessorType.MIPS) {
                  throw new Ae(Strings.ff("Processor %s is not supported (no helper)", this.sY()));
               }

               this.gp = new QY(this);
            }

            this.Ab = true;
            return true;
         } catch (Ae | kB | IOException var3) {
            if (var3 instanceof kB) {
               JebCoreService.notifySilentExceptionToClient(var3);
            }

            Sc.catching(var3);
            if (this.pC != null) {
               try {
                  this.pC.close();
               } catch (IOException var2) {
               }

               this.pC = null;
            }

            return false;
         }
      }
   }

   private void DQ() throws IOException, Ae {
      this.wS(Strings.encodeASCII("+"));
      this.wS("QStartNoAckMode");
      this.fI = Q.pC(this);
      if (this.UO == jT.pC) {
         this.UO = this.fI != null && !this.fI.pC() ? jT.kS : jT.A;
      }

      if (this.UO == jT.kS) {
         if (this.fI != null) {
            ProcessorType var1 = this.fI.wS();
            if (var1 != null) {
               this.pC(var1);
            }

            Endianness var2 = this.fI.kS();
            if (var2 != null) {
               this.pC(var2);
            }
         }

         this.WR = KM.pC(this);
         JK var12 = new JK(new Hj(this));
         this.NS = var12.pC();
         this.vP = AS.pC(this);
         this.xC = zI.pC(this.wS("QThreadSuffixSupported"));
         this.wS("QListThreadsInStopReply");
         this.wS("QEnableErrorStrings");
      }

      String var13 = "qSupported";
      if (this.sY().isIntel()) {
         var13 = "qSupported:multiprocess+;xmlRegisters=i386;qRelocInsn+";
      }

      if (this.UO == jT.kS) {
         var13 = "qSupported:xmlRegisters=i386,arm,mips";
      }

      String var14 = Strings.decodeASCII(this.wS(var13));

      for (String var6 : var14.split(";")) {
         int var9 = var6.indexOf(61);
         String var7;
         String var8;
         if (var9 < 0) {
            var7 = var6.substring(0, var6.length() - 1);
            char var10 = var6.charAt(var6.length() - 1);
            switch (var10) {
               case '+':
                  var8 = "enabled";
                  break;
               case '-':
                  var8 = "disabled";
                  break;
               case '?':
                  var8 = "unknown";
                  break;
               default:
                  continue;
            }
         } else {
            var7 = var6.substring(0, var9);
            var8 = var6.substring(var9 + 1);
         }

         this.Cu.put(var7, var8);
      }

      this.wS("Hgp0.0");
      this.kS = new YC(this);
      this.hZ = Conversion.stringToInt((String)this.Cu.get("PacketSize"), 512, 16);
      if (this.Cu.containsKey("qXfer:features:read")) {
         try {
            this.os = new hq(new rA(this));
         } catch (Ae var11) {
            Sc.warn("Could not collect debugging server features list: %s", var11.toString());
         }

         if (this.os != null) {
            if (this.NS == null) {
               this.NS = this.os.kS();
            }

            Object[] var10000 = new Object[]{Strings.joinList(this.os.A())};
            this.rl = this.os.wS();
            if (this.rl == null) {
               this.rl = ProcessorUtil.fromArchName(this.os.pC());
            }

            if (this.rl.isIntel()) {
               this.pC(Endianness.LITTLE_ENDIAN);
            }
         }
      }

      this.A(false);
      if (this.hK != null && !this.hK.isEmpty()) {
         StringBuilder var15 = new StringBuilder();

         for (int var17 : new TreeSet(this.hK)) {
            Strings.ff(var15, "%X;", var17);
         }

         this.wS("QPassSignals:" + var15.toString());
      }

      if (this.NS == null || this.NS.A()) {
         Sc.warn("The debugger server did not provide registers layout information! Will attempt to retrieve a default layout...");
         this.NS = vA.pC(this.sY());
         if (this.NS == null || this.NS.A()) {
            throw new Ae("No default registers layout could be found!");
         }
      }

      Sc.debug("GDB Registers Layout: %s", this.NS);
   }

   public boolean A(String var1) {
      return Strings.equals((String)this.Cu.get(var1), "enabled");
   }

   public boolean A(boolean var1) {
      if (var1) {
         throw new RuntimeException("Non-stop mode is not supported yet");
      } else {
         String var2 = Strings.ff("QNonStop:%d", var1 ? 1 : 0);

         try {
            byte[] var3 = this.wS(var2);
            if (!Strings.equals(Strings.decodeASCII(var3), "OK")) {
               return false;
            }
         } catch (IOException var4) {
            Sc.catching(var4);
            return false;
         }

         this.UW = var1;
         return true;
      }
   }

   public boolean oT() {
      return this.UW;
   }

   public boolean fI() {
      return !this.UW;
   }

   public String WR() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (String var4 : this.Cu.keySet()) {
         if (var2 != 0) {
            var1.append("\n");
         }

         Strings.ff(var1, "- %s : %s", var4, this.Cu.get(var4));
         var2++;
      }

      var1.append("\n- vCont actions: " + this.kS);
      return var1.toString();
   }

   public Q NS() {
      return this.fI;
   }

   public KM vP() {
      return this.WR;
   }

   public AS xC() {
      return this.vP;
   }

   public Ti ED() {
      return this.NS;
   }

   Tl Sc() {
      return this.pC(null);
   }

   Tl pC(Map var1) {
      return new Tl(this.NS, this.z, var1);
   }

   Ez pC(byte[] var1) throws IOException, Ae {
      return new Ez(var1, this);
   }

   public int ah() {
      return this.hZ;
   }

   public qa eP() {
      return this.E;
   }

   public rr UO() {
      return this.sY;
   }

   public IProcessor Ab() {
      return this.gp != null ? this.gp.A() : null;
   }

   public XE rl() {
      return this.ys;
   }

   public IDebuggerVirtualMemory z() {
      return this.oT;
   }

   public OL Ek() {
      return this.ld;
   }

   public vK hK() {
      return new vK(this);
   }

   public void Er() {
      this.FE.pC(true);
   }

   @Override
   public synchronized boolean pC(boolean var1) {
      if (!this.pC()) {
         return false;
      } else {
         if (this.pC != null) {
            try {
               if (var1) {
                  this.wS(Fi.pC("k"));
               } else if (this.EX()) {
                  this.wS(Fi.pC("D"));
               }
            } catch (IOException var6) {
               Sc.catching(var6);
            }

            try {
               this.pC.close();
            } catch (IOException var5) {
               Sc.catching(var5);
            }

            this.pC = null;
         }

         if (this.Er != null) {
            try {
               this.Er.interrupt();
               this.Er.join();
            } catch (InterruptedException var4) {
               Sc.error("The GDB receiver was interrupted");
            }
         }

         if (this.FE != null) {
            this.FE.interrupt();

            try {
               this.FE.join();
            } catch (InterruptedException var3) {
               Sc.error("The GDB event processor was interrupted");
            }
         }

         return true;
      }
   }

   @Override
   public boolean kS() {
      try {
         return this.E.E();
      } catch (IOException var2) {
         Sc.catching(var2);
         return false;
      }
   }

   @Override
   public boolean wS() {
      return this.PR != null ? this.PR.A() : false;
   }

   @Override
   public boolean UT() {
      return this.pC() && this.EX();
   }

   List FE() {
      synchronized (this.LM) {
         return new ArrayList(this.LM);
      }
   }

   public void pC(ZB var1) {
      synchronized (this.LM) {
         this.LM.add(var1);
      }
   }

   public void A(ZB var1) {
      synchronized (this.LM) {
         this.LM.remove(var1);
      }
   }

   void pC(Fi var1) {
      int var2 = 0;
      if (this.cX.get() >= 1) {
         var2 = this.cX.getAndDecrement();
      }

      if (var2 == 0 && var1.kS()) {
         this.FE.pC(var1);
      } else {
         synchronized (this.mv) {
            this.sO.add(var1);
            this.mv.notify();
         }
      }
   }

   boolean Aj() {
      return this.UT;
   }

   boolean EX() {
      return !this.Aj();
   }

   void LM() {
      this.E.pC();
   }

   public String kS(String var1) throws IOException, Ae {
      String var2 = Strings.decodeASCII(this.pC(var1, true));
      zI.pC(var1, var2);
      return var2;
   }

   public byte[] wS(String var1) throws IOException {
      return this.pC(var1, true);
   }

   byte[] A(byte[] var1) throws IOException {
      return this.pC(var1, true);
   }

   byte[] pC(String var1, boolean var2) throws IOException {
      return this.pC(Strings.encodeASCII(var1), var2);
   }

   byte[] pC(byte[] var1, boolean var2) throws IOException {
      synchronized (this.wS) {
         if (this.UT) {
            throw new IOException("GDB is already expecting a response");
         } else {
            byte[] var4 = Fi.pC(var1);
            this.wS(var4);
            if (!var2) {
               Object[] var10000 = new Object[]{Formatter.escapeBytes(var1)};
               this.UT = true;
               return null;
            } else {
               Fi var5 = this.ZN();
               if (var5.A()) {
                  this.wS(Strings.encodeASCII("+"));
               }

               return var5.pC();
            }
         }
      }
   }

   private void kS(byte[] var1) {
      if (var1.length >= 2 && var1[0] == 36) {
         if (var1[1] == 120) {
            this.cX.incrementAndGet();
         } else if (var1[1] == 63) {
            this.cX.incrementAndGet();
         } else if (ArrayUtil.compareBytes(var1, 1, DQ, 0, DQ.length) == 0) {
            this.LM();
         }
      }
   }

   private void wS(byte[] var1) throws IOException {
      if (this.A) {
         Sc.trace("< Sending %d bytes > ( %s )", var1.length, Formatter.escapeBytes(var1));
      }

      if (this.fI() && this.Aj()) {
         Sc.warn("[!] GDB is expecting a response in all-stop mode, the stub may discard your request");
      }

      this.kS(var1);
      synchronized (this.EX) {
         this.Aj.write(var1);
      }
   }

   private Fi ZN() throws IOException {
      synchronized (this.mv) {
         int var3 = 0;

         while (this.sO.isEmpty()) {
            try {
               if (var3++ > 0) {
                  if (var3 == 2) {
                     Object[] var8 = new Object[0];
                  }

                  if (!this.Er.isAlive()) {
                     throw new IOException("The receiver is dead");
                  }
               }

               this.mv.wait(1000L);
               if (this.Ek >= 1 && var3 >= this.Ek) {
                  throw new IOException("A GDB debugger seems blocked: a synchronous query is not receiving a response");
               }
            } catch (InterruptedException var6) {
               throw new IOException(var6);
            }
         }

         return (Fi)this.sO.poll();
      }
   }

   public String pC(String var1, String var2) throws IOException, Ae {
      int var3 = 0;
      int var4 = this.ah() >> 2;
      StringBuilder var5 = new StringBuilder();

      while (true) {
         String var6 = Strings.ff("qXfer:%s:read:%s:%x,%x", var1, var2, var3, var4);
         String var7 = this.kS(var6);
         if (var7.startsWith("l")) {
            var5.append(var7.substring(1));
            break;
         }

         if (!var7.startsWith("m")) {
            break;
         }

         var5.append(var7.substring(1));
         var3 = var5.length();
      }

      return var5.toString();
   }

   @Override
   public String toString() {
      return Strings.ff("%s{%s:%d, %s-%s}", this.UO, this.ah, this.eP, this.rl, this.z);
   }

   public void pC(int var1, rs var2) {
      if (var2 != null && var2 != rs.pC) {
         this.ED.put(var1, var2);
      } else {
         this.ED.remove(var1);
      }
   }

   public List mv() {
      try {
         return this.E.A();
      } catch (IOException | Ae var2) {
         Sc.catching(var2);
         return null;
      }
   }

   public List sO() {
      try {
         return this.E.gp();
      } catch (IOException | Ae var2) {
         Sc.catching(var2);
         return null;
      }
   }

   public int os() {
      try {
         return this.E.ld();
      } catch (IOException | Ae var2) {
         Sc.error(var2.toString());
         return -1;
      }
   }

   public boolean A(int var1) {
      try {
         this.E.wS(var1);
         return true;
      } catch (IOException | Ae var3) {
         Sc.catching(var3);
         return false;
      }
   }

   public LD Cu() {
      try {
         return this.E.UT();
      } catch (IOException | Ae var2) {
         Sc.catching(var2);
         return null;
      }
   }

   public boolean pC(LD var1) {
      try {
         this.E.pC(var1);
         return true;
      } catch (IOException | Ae var3) {
         Sc.catching(var3);
         return false;
      }
   }

   public LD kS(int var1) {
      try {
         return this.E.A(var1);
      } catch (IOException | Ae var3) {
         Sc.catching(var3);
         return null;
      }
   }

   public boolean pC(int var1, LD var2) {
      try {
         this.E.pC(var2, var1);
         return true;
      } catch (IOException | Ae var4) {
         Sc.catching(var4);
         return false;
      }
   }

   public boolean hZ() {
      try {
         return this.E.pC(true);
      } catch (IOException | Ae var2) {
         Sc.catching(var2);
         return false;
      }
   }

   public boolean UW() {
      try {
         return this.E.pC(false);
      } catch (IOException | Ae var2) {
         Sc.catching(var2);
         return false;
      }
   }

   public boolean pC(long var1) {
      return this.ld.pC(var1);
   }

   public List PR() {
      return this.ld.pC();
   }

   public boolean pC(long var1, int var3) {
      try {
         return this.gp.A(var1, var3);
      } catch (IOException var5) {
         Sc.catching(var5);
         return false;
      }
   }

   public boolean A(long var1) {
      try {
         return this.ld.A(var1);
      } catch (IOException var4) {
         Sc.catching(var4);
         return false;
      }
   }

   public boolean cX() {
      try {
         return this.ld.A();
      } catch (IOException var2) {
         Sc.catching(var2);
         return false;
      }
   }

   public int pC(long var1, int var3, byte[] var4, int var5) {
      try {
         return this.ys.pC(var1, var4, var5, var3);
      } catch (IOException var7) {
         Sc.catching(var7);
         return -1;
      }
   }

   public int A(long var1, int var3, byte[] var4, int var5) {
      try {
         return this.ys.A(var1, var4, var5, var3);
      } catch (IOException var7) {
         Sc.catching(var7);
         return -1;
      }
   }

   public byte[] UT(String var1) {
      try {
         return this.sY.pC(var1);
      } catch (Ae | IOException var3) {
         Sc.catching(var3);
         return null;
      }
   }
}
