package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class oH extends fC implements Yq {
   private static final ILogger Hk = GlobalLog.getLogger(oH.class);
   boolean RF = true;
   private String Me;
   private int PV;
   private u oQ;
   private boolean xW;
   private ProcessorType KT = ProcessorType.UNKNOWN;
   private Endianness Gf = Endianness.LITTLE_ENDIAN;
   private int Ef;
   private Lq cC;
   private ON sH;
   private OutputStream CE;
   private Object wF = new Object();
   private List If = new ArrayList();
   private Object Dz = new Object();
   private Queue mI = new ConcurrentLinkedQueue();
   private YF jq;
   uG xK;
   private Map ui = new TreeMap();
   private int TX;
   Object Dw = new Object();
   volatile boolean Uv;
   private boolean Rr;
   private Xh EB;
   sr oW;
   qs gO;
   yw nf;
   UI gP;
   yi za;
   qR lm;
   jL zz;
   DL JY;
   pF HF;
   ob LK;
   boolean io;
   private AtomicInteger Xo = new AtomicInteger();
   private static final byte[] Bu = Strings.encodeASCII("vCont;");
   Map qa = Collections.synchronizedMap(new HashMap());

   public oH(int var1) {
      this(null, var1, null);
   }

   public oH(String var1, int var2) {
      this(var1, var2, null);
   }

   public oH(String var1, int var2, u var3) {
      if (var1 == null) {
         var1 = "localhost";
      }

      this.Me = var1;
      this.PV = var2;
      if (var3 == null) {
         var3 = u.q;
      }

      this.oQ = var3;
   }

   @Override
   public u gO() {
      return this.oQ;
   }

   @Override
   public void q(ProcessorType var1) {
      if (this.xW) {
         throw new IllegalStateException("The debugger is already connected to a target");
      } else {
         this.KT = var1;
      }
   }

   @Override
   public ProcessorType nf() {
      return this.KT;
   }

   @Override
   public void q(Endianness var1) {
      if (this.xW) {
         throw new IllegalStateException("The debugger is already connected to a target");
      } else {
         this.Gf = var1;
      }
   }

   @Override
   public Endianness gP() {
      return this.Gf;
   }

   @Override
   public void q(int var1) {
      this.Ef = var1;
   }

   @Override
   public int za() {
      return this.Ef;
   }

   @Override
   public void q(Xh var1) {
      this.EB = var1;
   }

   @Override
   public Xh lm() {
      return this.EB;
   }

   @Override
   public synchronized boolean xK() {
      if (this.xW) {
         throw new IllegalStateException("The debugger is already connected to a target");
      } else {
         try {
            this.q = new Socket(this.Me, this.PV);
            this.CE = this.q.getOutputStream();
            this.cC = new Lq(this);
            this.cC.setDaemon(true);
            this.cC.start();
            this.sH = new ON(this);
            this.sH.setDaemon(true);
            this.sH.start();
            this.eJ();
            this.oW = new sr(this);
            this.gO = new qs(this);
            this.nf = new yw(this, this.nf().is64Bit() ? 64 : 32, 4096);
            this.lm = new qR(this);
            this.gP = new UI(this);
            if (this.nf() == ProcessorType.ARM) {
               this.za = new c(this);
            } else if (this.nf() == ProcessorType.ARM64) {
               this.za = new MQ(this);
            } else if (this.nf() == ProcessorType.X86) {
               this.za = new ml(this);
            } else if (this.nf() == ProcessorType.X86_64) {
               this.za = new j(this);
            } else {
               if (this.nf() != ProcessorType.MIPS) {
                  throw new WI(Strings.ff("Processor %s is not supported (no helper)", this.nf()));
               }

               this.za = new Wz(this);
            }

            this.xW = true;
            return true;
         } catch (WI | uS | IOException var3) {
            if (var3 instanceof uS) {
               JebCoreService.notifySilentExceptionToClient(var3);
            }

            Hk.catching(var3);
            if (this.q != null) {
               try {
                  this.q.close();
               } catch (IOException var2) {
               }

               this.q = null;
            }

            return false;
         }
      }
   }

   private void eJ() throws IOException, WI {
      this.Dw(Strings.encodeASCII("+"));
      this.Dw("QStartNoAckMode");
      this.zz = jL.q(this);
      if (this.oQ == u.q) {
         this.oQ = this.zz != null && !this.zz.q() ? u.xK : u.RF;
      }

      if (this.oQ == u.xK) {
         if (this.zz != null) {
            ProcessorType var1 = this.zz.lm();
            if (var1 != null) {
               this.q(var1);
            }

            Endianness var2 = this.zz.Uv();
            if (var2 != null) {
               this.q(var2);
            }
         }

         this.JY = DL.q(this);
         vD var12 = new vD(new aV(this));
         this.HF = var12.q();
         this.LK = ob.q(this);
         this.io = MO.RF(this.Dw("QThreadSuffixSupported"));
         this.Dw("QListThreadsInStopReply");
         this.Dw("QEnableErrorStrings");
      }

      String var13 = "qSupported";
      if (this.nf().isIntel()) {
         var13 = "qSupported:multiprocess+;xmlRegisters=i386;qRelocInsn+";
      }

      if (this.oQ == u.xK) {
         var13 = "qSupported:xmlRegisters=i386,arm,mips";
      }

      String var14 = Strings.decodeASCII(this.Dw(var13));

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

         this.ui.put(var7, var8);
      }

      this.Dw("Hgp0.0");
      this.xK = new uG(this);
      this.TX = Conversion.stringToInt((String)this.ui.get("PacketSize"), 512, 16);
      if (this.ui.containsKey("qXfer:features:read")) {
         try {
            this.jq = new YF(new kS(this));
         } catch (WI var11) {
            Hk.warn("Could not collect debugging server features list: %s", var11.toString());
         }

         if (this.jq != null) {
            if (this.HF == null) {
               this.HF = this.jq.Dw();
            }

            Object[] var10000 = new Object[]{Strings.joinList(this.jq.xK())};
            this.KT = this.jq.Uv();
            if (this.KT == null) {
               this.KT = ProcessorUtil.fromArchName(this.jq.q());
            }

            if (this.KT.isIntel()) {
               this.q(Endianness.LITTLE_ENDIAN);
            }
         }
      }

      this.Dw(
         "QProgramSignals:0;1;3;4;6;7;8;9;a;b;c;d;e;f;10;11;12;13;14;15;16;17;18;19;1a;1b;1c;1d;1e;1f;20;21;22;23;24;25;26;27;28;29;2a;2b;2c;2d;2e;2f;30;31;32;33;34;35;36;37;38;39;3a;3b;3c;3d;3e;3f;40;41;42;43;44;45;46;47;48;49;4a;4b;4c;4d;4e;4f;50;51;52;53;54;55;56;57;58;59;5a;5b;5c;5d;5e;5f;60;61;62;63;64;65;66;67;68;69;6a;6b;6c;6d;6e;6f;70;71;72;73;74;75;76;77;78;79;7a;7b;7c;7d;7e;7f;80;81;82;83;84;85;86;87;88;89;8a;8b;8c;8d;8e;8f;90;91;92;93;94;95;96;"
      );
      this.RF(false);
      this.Dw("QPassSignals:e;10;14;17;1a;1b;1c;21;24;25;2c;2d;4c;");
      if (this.HF == null || this.HF.RF()) {
         Hk.warn("The debugger server did not provide registers layout information! Will attempt to retrieve a default layout...");
         this.HF = Da.q(this.nf());
         if (this.HF == null || this.HF.RF()) {
            throw new WI("No default registers layout could be found!");
         }
      }

      Hk.debug("GDB Registers Layout: %s", this.HF);
   }

   public boolean q(String var1) {
      return Strings.equals((String)this.ui.get(var1), "enabled");
   }

   public boolean RF(String var1) {
      return this.jq.q(var1);
   }

   public boolean RF(boolean var1) {
      if (var1) {
         throw new RuntimeException("Non-stop mode is not supported yet");
      } else {
         String var2 = Strings.ff("QNonStop:%d", var1 ? 1 : 0);

         try {
            byte[] var3 = this.Dw(var2);
            if (!Strings.equals(Strings.decodeASCII(var3), "OK")) {
               return false;
            }
         } catch (IOException var4) {
            Hk.catching(var4);
            return false;
         }

         this.Rr = var1;
         return true;
      }
   }

   public boolean zz() {
      return this.Rr;
   }

   public boolean JY() {
      return !this.Rr;
   }

   public String HF() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (String var4 : this.ui.keySet()) {
         if (var2 != 0) {
            var1.append("\n");
         }

         Strings.ff(var1, "- %s : %s", var4, this.ui.get(var4));
         var2++;
      }

      var1.append("\n- vCont actions: " + this.xK);
      return var1.toString();
   }

   public jL LK() {
      return this.zz;
   }

   public DL io() {
      return this.JY;
   }

   public ob qa() {
      return this.LK;
   }

   public pF Hk() {
      return this.HF;
   }

   kW Me() {
      return this.q(null);
   }

   kW q(Map var1) {
      return new kW(this.HF, this.Gf, var1);
   }

   Yl q(byte[] var1) throws IOException, WI {
      return new Yl(var1, this);
   }

   public int PV() {
      return this.TX;
   }

   public sr oQ() {
      return this.oW;
   }

   public qs xW() {
      return this.gO;
   }

   public IProcessor KT() {
      return this.za != null ? this.za.RF() : null;
   }

   public yw Gf() {
      return this.nf;
   }

   public IDebuggerVirtualMemory Ef() {
      return this.lm;
   }

   public UI cC() {
      return this.gP;
   }

   public RS sH() {
      return new RS(this);
   }

   public void CE() {
      this.sH.q(true);
   }

   @Override
   public synchronized boolean q(boolean var1) {
      if (!this.q()) {
         return false;
      } else {
         if (this.q != null) {
            try {
               if (var1) {
                  this.Dw(cq.q("k"));
               } else if (this.Dz()) {
                  this.Dw(cq.q("D"));
               }
            } catch (IOException var6) {
               Hk.catching(var6);
            }

            try {
               this.q.close();
            } catch (IOException var5) {
               Hk.catching(var5);
            }

            this.q = null;
         }

         if (this.cC != null) {
            try {
               this.cC.interrupt();
               this.cC.join();
            } catch (InterruptedException var4) {
               Hk.error("The GDB receiver was interrupted");
            }
         }

         if (this.sH != null) {
            this.sH.interrupt();

            try {
               this.sH.join();
            } catch (InterruptedException var3) {
               Hk.error("The GDB event processor was interrupted");
            }
         }

         return true;
      }
   }

   @Override
   public boolean Dw() {
      try {
         return this.oW.oW();
      } catch (IOException var2) {
         Hk.catching(var2);
         return false;
      }
   }

   @Override
   public boolean Uv() {
      return this.EB != null ? this.EB.RF() : false;
   }

   @Override
   public boolean oW() {
      return this.q() && this.Dz();
   }

   List wF() {
      synchronized (this.If) {
         return new ArrayList(this.If);
      }
   }

   @Override
   public void q(HJ var1) {
      synchronized (this.If) {
         this.If.add(var1);
      }
   }

   @Override
   public void RF(HJ var1) {
      synchronized (this.If) {
         this.If.remove(var1);
      }
   }

   void q(cq var1) {
      int var2 = 0;
      if (this.Xo.get() >= 1) {
         var2 = this.Xo.getAndDecrement();
      }

      if (var2 == 0 && var1.Uv()) {
         this.sH.q(var1);
      } else {
         synchronized (this.Dz) {
            this.mI.add(var1);
            this.Dz.notify();
         }
      }
   }

   boolean If() {
      return this.Uv;
   }

   boolean Dz() {
      return !this.If();
   }

   void mI() {
      this.oW.q();
   }

   public String xK(String var1) throws IOException, WI {
      String var2 = Strings.decodeASCII(this.q(var1, true));
      MO.q(var1, var2);
      return var2;
   }

   public byte[] Dw(String var1) throws IOException {
      return this.q(var1, true);
   }

   byte[] RF(byte[] var1) throws IOException {
      return this.q(var1, true);
   }

   byte[] q(String var1, boolean var2) throws IOException {
      return this.q(Strings.encodeASCII(var1), var2);
   }

   byte[] q(byte[] var1, boolean var2) throws IOException {
      synchronized (this.Dw) {
         if (this.Uv) {
            throw new IOException("GDB is already expecting a response");
         } else {
            byte[] var4 = cq.q(var1);
            this.Dw(var4);
            if (!var2) {
               Object[] var10000 = new Object[]{Formatter.escapeBytes(var1)};
               this.Uv = true;
               return null;
            } else {
               cq var5 = this.YN();
               if (var5.Dw()) {
                  this.Dw(Strings.encodeASCII("+"));
               }

               return var5.xK();
            }
         }
      }
   }

   private void xK(byte[] var1) {
      if (var1.length >= 2 && var1[0] == 36) {
         if (var1[1] == 120) {
            this.Xo.incrementAndGet();
         } else if (var1[1] == 63) {
            this.Xo.incrementAndGet();
         } else if (ArrayUtil.compareBytes(var1, 1, Bu, 0, Bu.length) == 0) {
            this.mI();
         }
      }
   }

   private void Dw(byte[] var1) throws IOException {
      if (this.RF) {
         Hk.trace("< Sending %d bytes > ( %s )", var1.length, Formatter.escapeBytes(var1));
      }

      if (this.JY() && this.If()) {
         Hk.warn("[!] GDB is expecting a response in all-stop mode, the stub may discard your request");
      }

      this.xK(var1);
      synchronized (this.wF) {
         this.CE.write(var1);
      }
   }

   private cq YN() throws IOException {
      synchronized (this.Dz) {
         int var3 = 0;

         while (this.mI.isEmpty()) {
            try {
               if (var3++ > 0) {
                  if (var3 == 2) {
                     Object[] var8 = new Object[0];
                  }

                  if (!this.cC.isAlive()) {
                     throw new IOException("The receiver is dead");
                  }
               }

               this.Dz.wait(1000L);
               if (this.Ef >= 1 && var3 >= this.Ef) {
                  throw new IOException("A GDB debugger seems blocked: a synchronous query is not receiving a response");
               }
            } catch (InterruptedException var6) {
               throw new IOException(var6);
            }
         }

         return (cq)this.mI.poll();
      }
   }

   public String q(String var1, String var2) throws IOException, WI {
      int var3 = 0;
      int var4 = this.PV() >> 2;
      StringBuilder var5 = new StringBuilder();

      while (true) {
         String var6 = Strings.ff("qXfer:%s:read:%s:%x,%x", var1, var2, var3, var4);
         String var7 = this.xK(var6);
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

   public Map jq() {
      return this.ui;
   }

   @Override
   public String toString() {
      return Strings.ff("%s{%s:%d, %s-%s}", this.oQ, this.Me, this.PV, this.KT, this.Gf);
   }

   @Override
   public void q(int var1, tV var2) {
      if (var2 != null && var2 != tV.q) {
         this.qa.put(var1, var2);
      } else {
         this.qa.remove(var1);
      }
   }

   @Override
   public void q(Collection var1, tV var2) {
      for (int var4 : var1) {
         this.q(var4, var2);
      }
   }

   @Override
   public tV RF(int var1) {
      tV var2 = (tV)this.qa.get(var1);
      return var2 == null ? tV.q : var2;
   }

   @Override
   public List ui() {
      try {
         return this.oW.RF();
      } catch (IOException | WI var2) {
         Hk.catching(var2);
         return null;
      }
   }

   @Override
   public List TX() {
      try {
         return this.oW.za();
      } catch (IOException | WI var2) {
         Hk.catching(var2);
         return null;
      }
   }

   @Override
   public int Rr() {
      try {
         return this.oW.gP();
      } catch (IOException | WI var2) {
         Hk.error(var2.toString());
         return -1;
      }
   }

   @Override
   public boolean xK(int var1) {
      try {
         this.oW.Dw(var1);
         return true;
      } catch (IOException | WI var3) {
         Hk.catching(var3);
         return false;
      }
   }

   @Override
   public Ht EB() {
      try {
         return this.oW.Uv();
      } catch (IOException | WI var2) {
         Hk.catching(var2);
         return null;
      }
   }

   @Override
   public boolean q(Ht var1) {
      try {
         this.oW.q(var1);
         return true;
      } catch (IOException | WI var3) {
         Hk.catching(var3);
         return false;
      }
   }

   @Override
   public Ht Dw(int var1) {
      try {
         return this.oW.RF(var1);
      } catch (IOException | WI var3) {
         Hk.catching(var3);
         return null;
      }
   }

   @Override
   public boolean q(int var1, Ht var2) {
      try {
         this.oW.q(var2, var1);
         return true;
      } catch (IOException | WI var4) {
         Hk.catching(var4);
         return false;
      }
   }

   @Override
   public byte[] q(int var1, String var2) {
      try {
         RegisterDescriptionEntry var3 = this.HF.getDescriptionEntryByName(var2);
         return var3 == null ? null : this.oW.q(var3.getNumber(), var1);
      } catch (IOException | WI var4) {
         Hk.catching(var4);
         return null;
      }
   }

   @Override
   public boolean q(int var1, String var2, byte[] var3) {
      try {
         RegisterDescriptionEntry var4 = this.HF.getDescriptionEntryByName(var2);
         if (var4 == null) {
            return false;
         } else {
            this.oW.q(var4.getNumber(), var3, var1);
            return true;
         }
      } catch (IOException | WI var5) {
         Hk.catching(var5);
         return false;
      }
   }

   @Override
   public boolean Xo() {
      try {
         return this.oW.q(true);
      } catch (IOException | WI var2) {
         Hk.catching(var2);
         return false;
      }
   }

   @Override
   public boolean Bu() {
      try {
         return this.oW.q(false);
      } catch (IOException | WI var2) {
         Hk.catching(var2);
         return false;
      }
   }

   @Override
   public boolean q(long var1) {
      return this.gP.RF(var1);
   }

   @Override
   public fQ RF(long var1) {
      return this.gP.q(var1);
   }

   @Override
   public List IN() {
      return this.gP.q();
   }

   @Override
   public boolean q(long var1, int var3) {
      try {
         return this.za.RF(var1, var3);
      } catch (IOException var5) {
         Hk.catching(var5);
         return false;
      }
   }

   @Override
   public boolean xK(long var1) {
      try {
         return this.gP.xK(var1);
      } catch (IOException var4) {
         Hk.catching(var4);
         return false;
      }
   }

   @Override
   public boolean rL() {
      try {
         return this.gP.RF();
      } catch (IOException var2) {
         Hk.catching(var2);
         return false;
      }
   }

   @Override
   public int q(long var1, int var3, byte[] var4, int var5) {
      try {
         return this.nf.q(var1, var4, var5, var3);
      } catch (IOException var7) {
         Hk.catching(var7);
         return -1;
      }
   }

   @Override
   public int RF(long var1, int var3, byte[] var4, int var5) {
      try {
         return this.nf.RF(var1, var4, var5, var3);
      } catch (IOException var7) {
         Hk.catching(var7);
         return -1;
      }
   }

   @Override
   public byte[] Uv(String var1) {
      try {
         return this.gO.q(var1);
      } catch (WI | IOException var3) {
         Hk.catching(var3);
         return null;
      }
   }
}
