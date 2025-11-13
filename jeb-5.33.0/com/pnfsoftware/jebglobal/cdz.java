package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuClass;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmuUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryChangesRecorder;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StorageEntry;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.corei.parsers.arm.converter.ArmEmu_CRC;
import com.pnfsoftware.jeb.util.base.IniFileEditor;
import com.pnfsoftware.jeb.util.collect.BiMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.jetty.util.IO;

public class cdz {
   static final ILogger pC = GlobalLog.getLogger(cdz.class);
   static int A = 1000000000;
   INativeCodeUnit kS;
   ITypeManager wS;
   ICallingConvention UT;
   IProcessor E;
   ProcessorType sY;
   int ys;
   int ld;
   INativeDecompilerUnit gp;
   IEGlobalContext oT;
   Map fI = new HashMap();
   public cee WR;
   public cem NS;
   long vP;
   long xC;
   IDState ED;
   private boolean ZN;
   EEmulator Sc;
   INativeType ah;
   INativeType eP;
   INativeType UO;
   long Ab;
   long rl;
   long z;
   long Ek;
   long hK;
   private long OB;
   private long pF;
   private BiMap Bc = new BiMap();
   private BiMap OI = new BiMap();
   private int Bf = 1;
   private BiMap Pe = new BiMap();
   private int ck = 1;
   private BiMap RW = new BiMap();
   private BiMap e = new BiMap();
   private cei xM;
   private ceg kU;
   private int Kq;
   private Map go = new HashMap();
   int Er;
   cdy FE;
   cel Aj;
   long EX = 1155071L;
   long LM = 0L;
   private long JF;
   private long Nq;
   private boolean pg;
   public int mv = -1;
   final int sO = 256;
   final long os = 57662133764096L;
   final int Cu = 32768;
   final long hZ = 57662133796864L;
   final int UW = 256;
   final long PR = 57662116986880L;
   final int cX = 32768;
   final long DQ = 57662117019648L;
   private MemoryChangesRecorder.Results gj;
   private static final String ZD = ckx.pC(new byte[]{109, 0, 47, 28, 24, 43, 45, 23, 22, 28, 3, 26, 19, 23}, 1, 50);
   private boolean DL;
   private boolean UH;
   private boolean VD;

   public cdz(IDState var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.ED = var1;
         IDexDecompilerUnit var2 = var1.getGlobalContext().getDecompiler();
         INativeCodeUnit var4 = (INativeCodeUnit)var2.getUnitProcessor().process("unipbcu", new BytesInput(new byte[0]), var2, "arm64", true, true);
         var4.setSubsystemType(SubsystemType.LINUX);
         var4.setCompilerType(CompilerType.GCC);
         var4.process();
         var4.getTypeLibraryService().load(ProcessorType.ARM64, 3);
         if (var1.getEmulatedEnvironment() != null) {
            this.FE = (cdy)var1.getEmulatedEnvironment().getValue(0);
         } else {
            this.FE = cdy.pC(var2.getParentProject());
         }

         this.pC(var4.getMemory());
         this.kS = var4;
         this.gp = var4.getDecompiler();
         this.Sc = EEmulator.createStandard(this.gp.getGlobalContext(), A);
         this.vP();
      }
   }

   private void vP() {
      this.wS = this.kS.getTypeManager();
      this.UT = this.wS.getCallingConventionManager().getDefaultConvention();
      this.E = this.kS.getProcessor();
      this.sY = this.E.getType();
      this.ys = this.wS.getPointerSize();
      this.ld = this.wS.getSlotSize();
      this.oT = this.gp.getGlobalContext();
      IVirtualMemory var1 = this.Sc.getVirtualMemory();
      this.OB = VirtualMemoryUtil.allocate(var1, 2063597568L, 8192, 7);
      this.pF = this.OB;
      this.WR = new cee(var1);
      if (this.Ab == 0L) {
         this.Ab = EEmuUtil.setupGenericThreadLocalStorageInfo(this.Sc, 2130706432L, true);
      } else {
         EEmuUtil.setupGenericThreadLocalStorageInfo(this.Sc, this.Ab, false);
      }

      this.NS = this.Sc();
      this.vP = 4294967296L;
      String var2 = ckx.pC(new byte[]{108, 13, 25, 23, 93, 8, 23, 24, 43, 80, 90, 12, 74, 69, 66, 74, 15, 7}, 2, 8);
      String var3 = ckx.pC(new byte[]{82, 67, 5, 11, 1, 77, 93, 28}, 1, 125);
      String var4 = ckx.pC(new byte[]{108, 3, 25, 27, 17, 66, 76, 70, 7, 79}, 2, 46);
      String var5 = ckx.pC(new byte[]{108, 3, 25, 27, 30, 6, 0, 70, 7, 79}, 2, 44);
      String var6 = ckx.pC(new byte[]{108, 3, 25, 27, 8, 71, 20, 7}, 2, 39);
      String var7 = ckx.pC(new byte[]{108, 3, 25, 27, 19, 7, 3, 26, 27, 73, 76, 77, 90, 79}, 2, 201);
      String var8 = ckx.pC(new byte[]{108, 3, 25, 27, 19, 27, 19, 70, 7, 79}, 2, 48);
      String var9 = ckx.pC(new byte[]{68, 67, 5, 11, 3, 15, 10, 22, 29, 6, 13, 59, 45, 7, 27, 26, 29, 4, 8, 75, 93, 28}, 1, 107);
      this.NS
         .pC(
            var8x -> var8x.ys.endsWith(var2)
               || var8x.ys.contains(var3)
               || var8x.ys.contains(var4)
               || var8x.ys.contains(var5)
               || var8x.ys.contains(var6)
               || var8x.ys.contains(var7)
               || var8x.ys.contains(var8)
               || var8x.ys.contains(var9)
               || var8x.ys.contains("stack_and_tls:main")
         );
      this.A(var1);
      this.Aj = new cel(this);
      this.JF = 490673381376L;
      String var10 = this.ys();
      IInput var11 = this.kS() == null ? null : this.kS().getApk().getInput();
      int var12 = var11 == null ? 1048576 : (int)var11.getCurrentSize();
      int var13 = (int)var1.roundToSize(var12);
      this.Nq = this.JF + var13;
      this.NS.pC(this.JF, this.Nq, 9, 0L, var10);
      this.Sc.getState().registerHooks(new cea(this), false);
      if (this.ld() != null) {
         this.mv = this.Aj.pC(var10, this.Aj.pC("O_RDONLY"), 0);
      }

      this.ED();
      this.xC();
   }

   public long pC() {
      if (!this.pg) {
         this.pg = true;
         IVirtualMemory var1 = this.Sc.getVirtualMemory();

         try {
            IInput var2 = this.kS().getApk().getInput();
            int var3 = (int)var2.getCurrentSize();
            var1.allocate(this.JF, var3, 1);

            try (InputStream var4 = this.kS().getApk().getInput().getStream()) {
               byte[] var5 = IO.readBytes(var4);
               var1.write(this.JF, var3, var5, 0, true);
            }
         } catch (Exception var9) {
         }
      }

      return this.JF;
   }

   public long pC(int var1) {
      return 57662133764096L + var1 * 256;
   }

   private boolean xC() {
      try {
         this.Sc.getVirtualMemory().allocate(57662133764096L, 32768, 3);
      } catch (MemoryException var1) {
         return false;
      }

      this.Sc.getState().registerHooks(new ceb(this), false);
      return true;
   }

   public long A(int var1) {
      return 57662116986880L + var1 * 256;
   }

   private boolean ED() {
      try {
         this.Sc.getVirtualMemory().allocate(57662116986880L, 32768, 3);
      } catch (MemoryException var1) {
         return false;
      }

      this.Sc.getState().registerHooks(new cec(this), false);
      return true;
   }

   public byte[] kS(int var1) {
      long var2 = this.A(var1) + 8L;
      IVirtualMemory var4 = this.Sc.getVirtualMemory();

      try {
         long var5 = var4.readLong(var2);
         int var7 = var4.readInt(var5 + 32L);
         byte[] var8 = new byte[var7];
         var4.read(var5, var8.length, var8, 0);
         return var8;
      } catch (MemoryException var9) {
         return null;
      }
   }

   public void A() throws EvaluationException {
      TypeLibraryService var1 = this.kS.getTypeLibraryService();
      this.ah = var1.findTypeBySignature(ckx.pC(new byte[]{-83, 43, 23, 23, 55, 27}, 1, 231), null);
      this.eP = var1.findTypeBySignature(ckx.pC(new byte[]{-70, 4, 7, 12, 43, 24}, 1, 240), null);
      this.UO = var1.findTypeBySignature(ckx.pC(new byte[]{9, 33, 57, 55, 19, 29, 14, 30, 17, 109, 77, 23, 65, 79, 85}, 2, 87), null);
      this.xM = new cei(this);
      this.kU = new ceg(this);
      IStructureType var2 = (IStructureType)((IReferenceType)TypeUtil.getNonAlias(this.ah, IReferenceType.class)).getPointedType();
      IStructureType var3 = (IStructureType)((IReferenceType)TypeUtil.getNonAlias(this.eP, IReferenceType.class)).getPointedType();
      IStructureType[] var4 = new IStructureType[]{var2, var3};

      for (IStructureType var8 : var4) {
         for (IStructureTypeField var10 : var8.getFields()) {
            INativeType var11 = TypeUtil.getNonAlias(var10.getType());
            if (TypeUtil.isFunctionPointer(var11)) {
               IPrototypeItem var12 = (IPrototypeItem)((IReferenceType)var11).getPointedType();
               this.pC(var10.getName(), var12);
            }
         }
      }

      IVirtualMemory var16 = this.Sc.getVirtualMemory();
      long var17 = VirtualMemoryUtil.allocate(var16, this.OB + 65536L, 4096, 7);
      long var18 = var17;

      try {
         this.rl = var18;
         this.z = this.rl + this.ys;
         var16.writePointer(var18, this.z);
         var18 = this.z;

         for (IStructureTypeField var25 : var2.getFields()) {
            INativeType var27 = TypeUtil.getNonAlias(var25.getType());
            if (TypeUtil.isFunctionPointer(var27)) {
               long var13 = (Long)this.Bc.getKeyForValue(var25.getName());
               var16.writePointer(var18 + var25.getOffset(), var13);
            }
         }

         var18 += var2.getSize();
         this.Ek = var18;
         this.hK = this.Ek + this.ys;
         var16.writePointer(var18, this.hK);
         var18 = this.hK;

         for (IStructureTypeField var26 : var3.getFields()) {
            INativeType var28 = TypeUtil.getNonAlias(var26.getType());
            if (TypeUtil.isFunctionPointer(var28)) {
               long var29 = (Long)this.Bc.getKeyForValue(var26.getName());
               var16.writePointer(var18 + var26.getOffset(), var29);
            }
         }

         var3.getSize();
      } catch (MemoryException var15) {
         throw new RuntimeException(var15);
      }

      this.xC = this.pC(ckx.pC(new byte[]{83, 0, 47, 28, 24, 43, 45, 23, 22, 28, 3, 26, 19, 23}, 1, 12), null);
      Object[] var10000 = new Object[]{this.xC};
      if (this.ED != null) {
         String var24 = DexUtil.findPackageName(this.ED.getGlobalContext().getDex());
         if (var24 != null) {
            this.Sc.getMetadata().processName = var24.substring(1);
         }
      }

      this.Sc.registerHooks(this.xM, true);
      this.Sc.registerHooks(this.kU, true);
      this.Sc.registerHooks(new cef(this), true);
      this.Sc.registerHooks(new cdz.Av(), true);
      this.Sc.setup();
   }

   private void pC(IVirtualMemory var1) {
      if (this.FE != null) {
         if (this.Ab == 0L) {
            cep var2 = new cep(this.FE);

            try {
               var1.setLazyMemoryProvider(var2, true);
            } catch (Exception var6) {
               pC.catchingSilent(var6);
            }

            String var3;
            try {
               cdy.Sv var4 = this.FE.A(4);
               var3 = Strings.decodeUTF8(this.FE.pC(var4));
            } catch (IOException var5) {
               throw new RuntimeException(var5);
            }

            IniFileEditor var7 = new IniFileEditor(var3, '#');
            this.Ab = Conversion.stringToLong(var7.getValue(ckx.pC(new byte[]{23, 63, 57, 61, 32, 54, 34, 36, 68}, 2, 69)));
         }
      }
   }

   private cem Sc() {
      cem var1 = new cem();
      if (this.FE != null) {
         try {
            cdy.Sv var2 = this.FE.A(2);
            String var3 = Strings.decodeUTF8(this.FE.pC(var2));
            var1 = cem.A(var3);
         } catch (IOException var4) {
            pC.catching(var4);
         }
      }

      return var1;
   }

   private void A(IVirtualMemory var1) {
      System.currentTimeMillis();

      for (cem.Av var3 : this.NS.pC().values()) {
         if (var3.wS == 0L && var3.ys.endsWith(".so")) {
            this.WR.pC(var3.pC, true);
         }
      }

      System.currentTimeMillis();
   }

   public IEmulatedAndroid kS() {
      return this.ED == null ? null : this.ED.getEmulatedEnvironment();
   }

   void wS() {
      if (this.ED == null) {
         throw new IllegalStateException(ckx.pC(new byte[]{107, 43, 0, 1, 23, 83, 68, 1, 29, 28, 1, 6, 67, 69, 8, 24, 25, 13, 21, 27, 29}, 1, 37));
      }
   }

   private long pC(String var1, IPrototypeItem var2) {
      Long var3 = (Long)this.Bc.getKeyForValue(var1);
      if (var3 != null) {
         return var3;
      } else {
         var3 = this.pF;
         this.Bc.put(var3, var1);
         this.pF += 16L;
         return var3;
      }
   }

   private void pC(int var1, String var2) {
      this.OI.put(var1, var2);
   }

   String wS(int var1) {
      String var2 = (String)this.OI.get(var1);
      if (var2 == null) {
         try {
            this.wS();
            var2 = ((IDEmuClass)this.ED.getObject(var1)).getFullName();
         } catch (DexDecEvaluationException var4) {
            throw new cej(
               ckx.pC(
                     new byte[]{
                        -40,
                        34,
                        15,
                        0,
                        1,
                        27,
                        84,
                        82,
                        23,
                        17,
                        6,
                        27,
                        12,
                        19,
                        19,
                        69,
                        69,
                        8,
                        24,
                        25,
                        13,
                        21,
                        17,
                        1,
                        68,
                        67,
                        15,
                        13,
                        18,
                        0,
                        83,
                        70,
                        9,
                        29,
                        82,
                        67,
                        15,
                        22,
                        19,
                        13,
                        68
                     },
                     1,
                     155
                  )
                  + var1,
               var4
            );
         } catch (Exception var5) {
            pC.catchingSilent(var5);
         }

         this.pC(var1, var2);
      }

      return var2;
   }

   int pC(String var1) throws DexDecEvaluationException {
      Integer var2 = (Integer)this.OI.getKeyForValue(var1);
      if (var2 == null) {
         if (this.ED == null) {
            this.ZN = true;
            var2 = 1 + this.OI.size();
         } else {
            var2 = this.ED.getClassReference(var1).getObjectReferenceId();
         }

         this.pC(var2, var1);
      }

      return var2;
   }

   int A(String var1) {
      Integer var2 = (Integer)this.RW.getKeyForValue(var1);
      if (var2 == null) {
         var2 = this.ck++;
         this.RW.put(var2, var1);
      }

      return var2;
   }

   String UT(int var1) {
      return (String)this.RW.get(var1);
   }

   int kS(String var1) {
      Integer var2 = (Integer)this.Pe.getKeyForValue(var1);
      if (var2 == null) {
         var2 = this.Bf++;
         this.Pe.put(var2, var1);
      }

      return var2;
   }

   String E(int var1) {
      return (String)this.Pe.get(var1);
   }

   void pC(String var1, String var2, String var3, long var4) {
      if (!var1.startsWith("[") && !var1.startsWith("L")) {
         var1 = "L" + var1 + ";";
      }

      String var6 = var1 + "->" + var2 + var3;
      Object[] var10000 = new Object[]{var6, var4};
      if (this.e.put(var4, var6) != null) {
         var10000 = new Object[0];
      }

      this.Sc.monitorHLSpecial(10, var6, var4);
   }

   int wS(String var1) {
      ArrayList var2 = new ArrayList();

      for (String var4 : this.e.values()) {
         if (var4.startsWith(var1)) {
            var2.add(var4);
         }
      }

      for (String var7 : var2) {
         Long var5 = (Long)this.e.removeValue(var7);
         if (var5 != null) {
            this.Sc.monitorHLSpecial(11, var7, var5);
         }
      }

      return var2.size();
   }

   public long UT() {
      return this.Ab;
   }

   public String E() {
      if (this.kS() != null) {
         return this.kS().getPackageName();
      } else {
         if (this.ED != null) {
            String var1 = DexUtil.findPackageName(this.ED.getGlobalContext().getDex());
            if (var1 != null) {
               return var1;
            }
         }

         return "com.somename";
      }
   }

   public String sY() {
      if (this.kS() != null) {
         return this.kS().getAppFolder();
      } else {
         String var1 = ckx.pC(
            new byte[]{
               108,
               11,
               17,
               13,
               19,
               70,
               6,
               24,
               4,
               15,
               86,
               29,
               89,
               105,
               92,
               81,
               117,
               97,
               121,
               18,
               93,
               6,
               1,
               99,
               85,
               18,
               37,
               69,
               22,
               47,
               47,
               9,
               99,
               6,
               83,
               89,
               0,
               74,
               1,
               13,
               51,
               12,
               20,
               111,
               53,
               75,
               40,
               8,
               22,
               58,
               22,
               12,
               17,
               70,
               70,
               87,
               30,
               62,
               34,
               13,
               51,
               56,
               90,
               85
            },
            2,
            246
         );
         return Strings.ff(var1, this.E());
      }
   }

   public String ys() {
      return this.kS() != null ? this.kS().getApkPath() : this.sY() + ckx.pC(new byte[]{-72, 77, 3, 18, 22, 75, 79, 17, 27}, 1, 151);
   }

   public IInput ld() {
      return this.kS() != null ? this.kS().getApk().getInput() : null;
   }

   public String gp() {
      return this.E();
   }

   public MemoryChangesRecorder.Results oT() {
      return this.gj;
   }

   public long pC(IELFUnit var1, boolean var2) {
      if (var1.getHeader().getType() != 3) {
         throw new IllegalArgumentException(
            ckx.pC(
               new byte[]{
                  -85,
                  61,
                  8,
                  21,
                  6,
                  23,
                  29,
                  7,
                  9,
                  71,
                  65,
                  65,
                  83,
                  27,
                  9,
                  19,
                  23,
                  1,
                  68,
                  79,
                  13,
                  8,
                  15,
                  6,
                  23,
                  84,
                  8,
                  123,
                  28,
                  102,
                  9,
                  76,
                  5,
                  11,
                  16,
                  19,
                  19,
                  11
               },
               1,
               238
            )
         );
      } else {
         String var3 = var1.getName();
         Long var10000 = (Long)this.fI.get(var3);
         EState var5 = this.Sc.getState();
         IVirtualMemory var6 = this.Sc.getVirtualMemory();
         long var7 = var1.getLoaderInformation().getImageSize();
         Long var4 = VirtualMemoryUtil.findAvailableRange(var6, this.vP, (int)var7);
         var1.map(var6, var4, true, new ced(this));
         long var9 = var6.roundToSize(var4 + var7);
         cee.Sv var11 = this.WR.pC(var4, true);
         String var12 = ckx.pC(new byte[]{50, 67, 5, 11, 77, 78, 19, 31, 91, 2, 27}, 1, 29);
         String var13 = this.sY() + var12 + var3;
         this.NS.pC(var4, var9, 5, 0L, var13);
         Object[] var31 = new Object[]{var3, var4, var9, this.NS.A()};
         long var14 = var11.A();
         if (var14 != 0L) {
            try {
               var6.writePointer(var14 + 8L, var4);
               var6.writePointer(var14 + 16L, this.xC);
            } catch (MemoryException var25) {
               throw new RuntimeException(var25);
            }
         }

         ArrayList var16 = new ArrayList();

         for (long var18 : var11.wS()) {
            if (var18 != 0L && var18 != -1L) {
               var16.add(var18);
            }
         }

         MemoryChangesRecorder var27 = null;
         if (var2) {
            var27 = new MemoryChangesRecorder(var6);
            var27.setup();
         }

         try {
            if (!var16.isEmpty()) {
               IPrototypeItem var28 = TypeUtil.buildQuickPrototype(this.wS, "void()");

               for (long var20 : var16) {
                  this.Sc.setGlobalRoutineEmulation(var20, var28);
                  this.Sc.run();
               }
            }

            long var29 = var11.pC(ckx.pC(new byte[]{-70, 4, 7, 22, 16, 33, 34, 35, 14, 5}, 1, 240));
            if (var29 != 0L) {
               IPrototypeItem var30 = TypeUtil.buildQuickPrototype(
                  this.wS, ckx.pC(new byte[]{92, 3, 7, 26, 92, 98, 43, 23, 23, 55, 27, 103, 6, 12, 86, 25, 6, 13, 78, 3}, 1, 54)
               );
               this.Sc.setGlobalRoutineEmulation(var29, var30);
               this.Sc.addArgument(this.rl, this.ah.getReference());
               this.Sc.addArgument(0L, this.wS.getVoidReference());
               boolean var21 = false;
               var5.enableMemoryWriteHitmap(var21, false);
               this.Sc.run();
               if (var21) {
                  var5.getMemoryWriteHitmap();
               }
            }
         } finally {
            if (var27 != null) {
               this.gj = var27.getMemoryChanges();
               var27.teardown();
            }
         }

         return var4;
      }
   }

   public Long pC(String var1, boolean var2, long var3, int var5, List var6) {
      JvmMethodSig var7 = JvmMethodSig.parse(var1);
      IPrototypeItem var8 = (IPrototypeItem)this.go.get(var1);
      if (var8 == null) {
         INativeType var9 = this.wS.getType(cek.pC(var7.rettype));
         ArrayList var10 = new ArrayList();
         var10.add(this.eP.getReference());
         var10.add(
            var2 ? this.wS.getType(ckx.pC(new byte[]{118, 9, 15, 13, 18, 0}, 1, 28)) : this.wS.getType(ckx.pC(new byte[]{41, 0, 18, 19, 23, 10, 19}, 2, 97))
         );

         for (String var12 : var7.partypes) {
            var10.add(this.wS.getType(cek.pC(var12)));
         }

         var8 = this.wS.createPrototype(var9, var10);
         this.go.put(var1, var8);
      }

      List var14 = var8.getParameterTypes();
      this.Sc.setGlobalRoutineEmulation(var3, var8);
      this.Sc.addArgument(this.Ek, (INativeType)var14.get(0));
      this.Sc.addArgument(var5, (INativeType)var14.get(1));
      int var15 = 2;

      for (long var18 : var6) {
         this.Sc.addArgument(var18, (INativeType)var14.get(var15));
         var15++;
      }

      this.Sc.run();
      if (var7.rettype.equals("V")) {
         return null;
      } else {
         TypeLayoutInfo var17 = TypeUtil.getLayoutInfo(var8.getReturnType());
         IEImm var19 = this.Sc.readStorage(var8.getCallingConvention().getOutput(var17, 0));
         return var19.getValue().longValue();
      }
   }

   public EEmulator fI() {
      return this.Sc;
   }

   public cel WR() {
      return this.Aj;
   }

   public Map NS() {
      return this.e.asReverseMap();
   }

   public Long UT(String var1) {
      return (Long)this.e.getKeyForValue(var1);
   }

   public int pC(boolean var1) {
      int var2 = this.Er;
      if (var1) {
         this.Er = 0;
      }

      return var2;
   }

   private int ah() throws MemoryException {
      EState var1 = this.Sc.getState();
      IVirtualMemory var2 = this.Sc.getVirtualMemory();
      if (this.sY == ProcessorType.ARM64) {
         long var3 = this.Sc.readStorage(StorageEntry.createStackSlot(0L)).getValueAsAddress();
         long var5 = this.Sc.readStorage(StorageEntry.createStackSlot(1L)).getValueAsAddress();
         long var7 = var1.getValueAsLongSafe(this.oT.getVariableByName("X16").getId());
         long var9 = var2.readPointer(var7 - 8L);
         cee.Sv var11 = this.WR.pC(var9, true);
         if (var11 == null) {
            return 0;
         } else {
            cee.K var12 = var11.pC(var3);
            if (var12 == null) {
               return 0;
            } else {
               String var13 = var12.pC();
               cee.Av var14 = this.WR.kS(var13);
               long var15;
               byte var17;
               if (var14 == null) {
                  var15 = this.Sc.createPseudoRoutine(var13);
                  var17 = 1;
               } else if (!var14.wS()) {
                  var15 = var14.pC();
                  this.Sc.registerRoutine(var15, var13);
                  var17 = 2;
               } else {
                  long var18 = var14.pC();

                  try {
                     EEmulator var20 = EEmulator.createStandard(this.oT, 100);
                     IPrototypeItem var21 = TypeUtil.buildQuickPrototype(
                        this.wS, ckx.pC(new byte[]{53, 0, 25, 29, 88, 65, 11, 7, 26, 71, 4, 67, 69, 79, 95, 94, 19, 26}, 2, 159)
                     );
                     var20.setGlobalRoutineEmulation(var18, var21);
                     var20.setup();
                     long var22 = 4611686018427387904L;
                     long var24 = var20.getSPAddress() - 24L;
                     var20.getVirtualMemory().writeLong(var24 + 0L, 24L);
                     var20.getVirtualMemory().writeLong(var24 + 8L, 0L);
                     var20.getVirtualMemory().writeLong(var24 + 16L, 0L);
                     var20.addArgument(var22, this.wS.getInteger(8, false));
                     var20.addArgument(var24, this.wS.getInteger(8, false));
                     var20.setReturnAddress(2864434397L);
                     var20.run();
                     var15 = var20.getReturnValue().getValueAsAddress();
                     this.Sc.registerRoutine(var15, var13);
                     var17 = 2;
                  } catch (Exception var26) {
                     pC.catching(var26);
                     var15 = this.Sc.createPseudoRoutine(var13);
                     var17 = 1;
                  }
               }

               this.Sc.writePointer(var3, var15);
               this.Sc.updateSPAddress(16);
               var1.setValue(this.oT.getVariableByName("X30"), var5);
               this.Sc.setPCAddress(var15);
               return var17;
            }
         }
      } else {
         return 0;
      }
   }

   class Av implements IEEmulatorHooks {
      @Override
      public Boolean evaluateAt(EEmulator var1, long var2, IInstruction var4) {
         if (var4 != null && cdz.this.sY == ProcessorType.ARM64) {
            long var5 = var1.getPCAddress();
            String var7 = var4.getMnemonic().toUpperCase();
            switch (ckx.kS(var7)) {
               case -1393601771:
                  String var8 = var4.getOperand(1).toString();
                  if (var8.equals("CNTVCT_EL0")) {
                     String var9 = var4.getOperand(0).toString();
                     IEVar var10 = var1.getGlobalContext().getVariableByName(var9);
                     var1.getState().setValue(var10, var1.getState().getEvaluationCount());
                     var1.setPCAddress(var5 + var4.getSize());
                     return true;
                  }
            }
         }

         String var12 = (String)cdz.this.Bc.get(var2);
         if (var12 != null) {
            if (var12.equals(cdz.ZD)) {
               try {
                  int var14 = cdz.this.ah();
                  if (var14 != 0) {
                     return true;
                  }
               } catch (MemoryException var11) {
               }

               return false;
            } else if (cdz.this.kU != null) {
               cdz.this.Kq++;
               var1.hooksEvaluateExternal(var12, null);
               return true;
            } else {
               return null;
            }
         } else {
            if (var12 == null && var1.findRegisteredRoutineByAddress(var2) == null) {
               cee.Av var6 = cdz.this.WR.pC(var2);
               if (var6 != null && var6.kS()) {
                  var12 = var6.A();
                  Object[] var10000 = new Object[]{var12};
                  var1.registerRoutine(var2, var12);
               }
            }

            return null;
         }
      }

      @Override
      public Boolean evaluateUntranslated(EEmulator var1, IEUntranslatedInstruction var2, IInstruction var3) {
         if (cdz.this.sY == ProcessorType.ARM64) {
            long var4 = var1.getPCAddress();
            String var6 = var3.getMnemonic().toUpperCase();
            EState var7 = var1.getState();
            switch (ckx.kS(var6)) {
               case -2065704008:
               case -1955475578:
               case -1707517867:
               case 9187363:
                  if (!cdz.this.UH) {
                     cdz.this.UH = true;
                     Object[] var40 = new Object[]{var7.getEvaluationCount(), var4};
                  }

                  IEVar var22 = (IEVar)var2.getParameterExpression(0);
                  IEVar var26 = (IEVar)var2.getParameterExpression(1);
                  IEImm var28 = var6.endsWith("MC") ? EUtil.zero(128) : cdz.this.fI().getState().getValue(var22);
                  IEImm var31 = var7.getValue(var26);
                  int[] var33 = EUtil.immToInt32Array(var28);
                  int[] var35 = EUtil.immToInt32Array(var31);
                  com.pnfsoftware.jeb.corei.parsers.arm.converter.uX.pC(var6, var33, var35);
                  IEImm var37 = EUtil.int32ArrayToImm(cdz.this.oT, var33);
                  var7.setValue(var22, var37);
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case -1938762596:
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case -1926101359:
               case -1588822308:
               case -246155291:
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case -1845478072:
               case -1423635851:
               case -895996318:
               case -631250284:
               case -127070849:
               case 1131941096:
               case 1654958862:
               case 1911269495:
                  if (!cdz.this.VD) {
                     cdz.this.VD = true;
                     Object[] var39 = new Object[]{var7.getEvaluationCount(), var4};
                  }

                  IEGeneric var21 = var2.getParameterExpression(0);
                  IEGeneric var25 = var2.getParameterExpression(1);
                  IEGeneric var27 = var2.getParameterExpression(2);
                  IEImm var30 = var25.evaluate(var7);
                  IEImm var32 = var27.evaluate(var7);
                  int var34 = (int)var30.getValueAsLong();
                  long var36 = var32.getValueAsLong();
                  int var38 = ArmEmu_CRC.pC(var6, var34, var36);
                  var7.setValue(var21, EUtil.imm(var38, 32));
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case -1703017091:
                  com.pnfsoftware.jeb.corei.parsers.arm.converter.Mh.pC(var7, EndianUtil.littleEndianBytesToInt(var3.getCode()));
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case -1373389767:
               case 601101668:
               case 1459091607:
               case 1880274289:
                  if (!cdz.this.DL) {
                     cdz.this.DL = true;
                     Object[] var10000 = new Object[]{var7.getEvaluationCount(), var4};
                  }

                  IEVar var20 = (IEVar)var2.getParameterExpression(0);
                  IEVar var24 = (IEVar)var2.getParameterExpression(1);
                  IEVar var10 = ckx.pC(9, 3159379, 1880274289, var6) ? null : (IEVar)var2.getParameterExpression(2);
                  IEImm var29 = var7.getValue(var20);
                  IEImm var12 = var7.getValue(var24);
                  IEImm var13 = var10 == null ? null : var7.getValue(var10);
                  int[] var14 = EUtil.immToInt32Array(var29);
                  int[] var15 = EUtil.immToInt32Array(var12);
                  int[] var16 = var13 == null ? null : EUtil.immToInt32Array(var13);
                  com.pnfsoftware.jeb.corei.parsers.arm.converter.Tb.pC(var6, var14, var15, var16);
                  IEImm var17 = EUtil.int32ArrayToImm(cdz.this.oT, var14);
                  var7.setValue(var20, var17);
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case -739719618:
               case -102588177:
               case 880510233:
                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case 845953756:
                  String var19 = var3.getOperand(0).toString();
                  if (!var19.equals("IVAU") && !var19.equals("IALLU")) {
                     var19.equals("IALLUIS");
                  }

                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case 1206220239:
                  String var18 = var3.getOperand(0).toString();
                  if (var18.equals("ZVA")) {
                     long var23 = var2.getParameterExpression(0).evaluateAddress(var7);

                     for (int var11 = 0; var11 < 64; var11++) {
                        if (!var7.writeMemoryByte(var23, (byte)0)) {
                           return false;
                        }

                        var23++;
                     }
                  }

                  var1.setPCAddress(var4 + var3.getSize());
                  return true;
               case 1674790780:
                  IEVar var8 = (IEVar)var2.getParameterExpression(0);
                  IEGeneric var9 = var2.getParameterExpression(1);
                  if (EUtil.isZero(var9)) {
                     var7.setValue(var8, EUtil.zero(var8.getBitsize()));
                     var1.setPCAddress(var4 + var3.getSize());
                     return true;
                  }
            }
         }

         return null;
      }
   }
}
