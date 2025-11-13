package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnitLock;
import com.pnfsoftware.jeb.core.units.PassthroughUnitLock;
import com.pnfsoftware.jeb.core.units.code.asm.items.IFieldManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.IMethodManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackage;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPackageManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IVirtualTableDefinition;
import com.pnfsoftware.jeb.core.units.code.asm.type.IllegalTypeNameException;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLibraryService;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeStringParser;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.ISimpleFilter;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@Ser
public class ayy implements IFieldManager, INativeItemListener, IClassManager, IPackageManager, ITypeManager {
   private static final ILogger pC = GlobalLog.getLogger(ayy.class);
   @SerId(1)
   private ayr A;
   @SerId(2)
   private int kS;
   @SerId(3)
   private ayh wS;
   @SerId(4)
   private Map UT = new ConcurrentHashMap();
   @SerId(5)
   private IdentityHashMap E = new IdentityHashMap();
   @SerId(6)
   private IdentityHashMap sY = new IdentityHashMap();
   @SerId(7)
   private List ys = new CopyOnWriteArrayList();
   @SerId(8)
   private List ld = new CopyOnWriteArrayList();
   @SerId(9)
   private IdentityHashMap gp = new IdentityHashMap();
   @SerId(10)
   private ayo oT;
   @SerId(11)
   private ko fI;
   @SerId(12)
   private ProcessorType WR;
   @SerId(13)
   private int NS;
   @SerId(15)
   private Map vP = new ConcurrentHashMap();
   @SerId(16)
   private int xC;
   @SerId(17)
   private Map ED = new ConcurrentHashMap();
   @SerId(18)
   private int Sc;
   @SerId(19)
   private Map ah = new ConcurrentHashMap();
   @SerId(20)
   private int eP;
   @SerId(21)
   private Map UO = new ConcurrentHashMap();
   @SerId(22)
   private int Ab;
   @SerId(23)
   private IUnitLock rl;
   @SerId(24)
   private SubsystemType z;
   @SerId(25)
   private CompilerType Ek;
   @SerId(26)
   private int hK;
   @SerId(27)
   private int Er;
   @SerId(28)
   private Map FE = new ConcurrentHashMap();
   @SerTransient
   private List Aj;
   @SerTransient
   private AtomicBoolean EX;
   @SerTransient
   private ayt LM;
   @SerTransient
   private Map mv = new ConcurrentHashMap();
   @SerTransient
   private TypeLibraryService sO;

   @SerCustomInitPostGraph
   private void NS() {
      this.Aj = new CopyOnWriteArrayList();
      this.EX = new AtomicBoolean(true);
      aye var1 = this.UT("void");
      this.LM = var1 == null ? null : var1.UT();
      if (this.hK == 0) {
         this.hK = this.kS;
      }
   }

   public ayy(ko var1, ProcessorType var2, SubsystemType var3, CompilerType var4, IPrimitiveSizes var5, int var6, int var7, String var8) {
      if (var1 instanceof INativeCodeUnit) {
         this.rl = ((INativeCodeUnit)var1).getLock();
         Assert.a(this.rl != null);
      }

      this.fI = var1;
      this.WR = var2;
      this.z = var3;
      this.Ek = var4;
      this.wS = new ayh(var2, var3, var4, var8);
      if (var5 == null) {
         var5 = PrimitiveSizes.getCommon(var2, var4);
      }

      if (var6 == 0) {
         var6 = var2.is64Bit() ? 8 : 4;
      }

      if (var7 == 0) {
         var7 = var6;
      }

      this.kS = var6;
      this.hK = var7;
      this.A = new ayr(this, (IPrimitiveSizes)var5, var6);
      this.oT = this.pC(null, null);
      this.NS();
   }

   public static ayy pC(ProcessorType var0, SubsystemType var1, CompilerType var2) {
      return new ayy(null, var0, var1, var2, null, 0, 0, null);
   }

   @Override
   public ProcessorType getProcessorType() {
      return this.WR;
   }

   @Override
   public SubsystemType getSubsystemType() {
      return this.z;
   }

   @Override
   public CompilerType getCompilerType() {
      return this.Ek;
   }

   @Override
   public int getPointerSize() {
      return this.kS;
   }

   @Override
   public int getSlotSize() {
      return this.hK;
   }

   public ayr pC() {
      return this.A;
   }

   public ayh A() {
      return this.wS;
   }

   public void pC(TypeLibraryService var1) {
      this.sO = var1;
   }

   @Override
   public TypeLibraryService getTypeLibraryService() {
      return this.sO;
   }

   public boolean kS() {
      return this.fI == null;
   }

   @Override
   public TypeStringParser getParser() {
      return new TypeStringParser(this);
   }

   public IUnitLock wS() {
      return (IUnitLock)(this.rl != null ? this.rl : PassthroughUnitLock.getInstance());
   }

   @Override
   public void addListener(INativeItemListener var1) {
      if (var1 != null) {
         this.Aj.add(var1);
      }
   }

   @Override
   public void removeListener(INativeItemListener var1) {
      if (var1 != null) {
         this.Aj.remove(var1);
      }
   }

   public void pC(NativeItemEvent var1) {
      if (this.EX.get()) {
         if (this.fI != null) {
            this.fI.onNativeItemEvent(var1);
         }

         for (INativeItemListener var3 : this.Aj) {
            if (var3 != var1.getItem()) {
               var3.onNativeItemEvent(var1);
            }
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof ayn) {
         ayn var2 = (ayn)var1.getItem();
         if (var2.kS() == this && ((auo)var2).isDisposed()) {
            ((auo)var2).removeListener(this);
         }
      }

      this.pC(var1);
   }

   private String pC(String var1, List var2, boolean var3) {
      this.wS().verifyLocked();
      if (var1 == null) {
         var1 = "__anon_" + Strings.randomUniqueId();
      }

      var1 = TypeUtil.preNormalizeSignature(var1);
      if (var1.endsWith("*")) {
         throw new IllegalTypeNameException("Signature is a reference type", var1);
      } else if (var1.endsWith("]")) {
         throw new IllegalTypeNameException("Signature is an array type", var1);
      } else {
         String var4 = TypeUtil.normalizeSignature(true, var1, var2, null);
         if (var4 == null) {
            throw new IllegalTypeNameException("Invalid signature", var1);
         } else if (!var3 && this.pC(var4, false) != null) {
            throw new IllegalTypeNameException("Type already exists", var4);
         } else {
            return var4;
         }
      }
   }

   private String pC(String var1, List var2) {
      return this.pC(var1, var2, false);
   }

   private ayx pC(String var1, List var2, ayx var3) {
      this.wS().verifyLocked();
      this.UT.put(var1, var3);
      return (ayx)this.A((aye)var3);
   }

   private aye A(aye var1) {
      this.wS().verifyLocked();
      var1.wS(this.NS);
      var1.pC(-8791026472627208192L | this.NS & 4294967295L);
      this.vP.put(this.NS, var1);
      this.NS++;
      var1.addListener(this);
      return var1;
   }

   public aye pC(long var1) {
      if ((var1 & -72057594037927936L) != -8791026472627208192L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.pC(var3);
      }
   }

   public aye pC(int var1) {
      return (aye)this.vP.get(var1);
   }

   public aym pC(String var1) {
      aym var6;
      try (ACLock var2 = this.wS().a()) {
         ArrayList var3 = new ArrayList();
         var1 = this.pC(var1, var3);
         String var4 = (String)var3.get(var3.size() - 1);
         aym var5 = new aym(this, var4, var1);
         var6 = (aym)this.pC(var1, var3, var5);
      }

      return var6;
   }

   public ayv pC(String var1, int var2, int var3) {
      ayv var8;
      try (ACLock var4 = this.wS().a()) {
         ArrayList var5 = new ArrayList();
         var1 = this.pC(var1, var5);
         String var6 = (String)var5.get(var5.size() - 1);
         ayv var7 = new ayv(this, var6, var1, var2, var3);
         var8 = (ayv)this.pC(var1, var5, var7);
      }

      return var8;
   }

   public ayv A(String var1) {
      return this.pC(var1, 1, 0);
   }

   public ayv pC(String var1, String... var2) {
      if (var2.length % 2 != 0) {
         throw new IllegalArgumentException("Odd number of entries for name-and-types list");
      } else {
         ayv var3 = this.pC(var1, 1, 0);

         for (byte var4 = 0; var4 < var2.length; var4 += 2) {
            String var5 = var2[var4];
            String var6 = var2[var4 + 1];
            if (this.addStructureField(var3, var5, var6) == null) {
               throw new IllegalArgumentException("Illegal field definition: " + var5 + " " + var6);
            }
         }

         return var3;
      }
   }

   public ayv kS(String var1) {
      return this.pC(var1, 0, 0);
   }

   @Override
   public IStructureTypeField addStructureField(IStructureType var1, String var2, String var3) {
      INativeType var4 = TypeUtil.buildQuickType(this, var3);
      return var4 == null ? null : this.addStructureField(var1, var2, var4);
   }

   @Override
   public IStructureTypeField addStructureField(IStructureType var1, String var2, INativeType var3) {
      return this.addStructureField(var1, var2, var3, -1, 0, 0, 0);
   }

   @Override
   public IStructureTypeField addStructureField(IStructureType var1, String var2, INativeType var3, int var4, int var5, int var6, int var7) {
      ayu var9;
      try (ACLock var8 = this.wS().a()) {
         var9 = ((ayv)var1).pC(var2, var3, var4, var5, var6, var7);
      }

      return var9;
   }

   @Override
   public boolean removeStructureField(IStructureType var1, IStructureTypeField var2) {
      boolean var4;
      try (ACLock var3 = this.wS().a()) {
         var4 = ((ayv)var1).pC(var2);
      }

      return var4;
   }

   @Override
   public boolean renameStructureField(IStructureType var1, String var2, String var3) {
      boolean var6;
      try (ACLock var4 = this.wS().a()) {
         ayu var5 = ((ayv)var1).pC(var2);
         if (var5 == null) {
            return false;
         }

         var6 = ((ayv)var1).pC(var5, var3);
      }

      return var6;
   }

   @Override
   public boolean renameStructureField(IStructureType var1, IStructureTypeField var2, String var3) {
      boolean var5;
      try (ACLock var4 = this.wS().a()) {
         var5 = ((ayv)var1).pC((ayu)var2, var3);
      }

      return var5;
   }

   public ayi wS(String var1) {
      ayi var3;
      try (ACLock var2 = this.wS().a()) {
         var3 = this.A(var1, 1, 1);
      }

      return var3;
   }

   public ayi A(String var1, int var2, int var3) {
      ayi var8;
      try (ACLock var4 = this.wS().a()) {
         ArrayList var5 = new ArrayList();
         var1 = this.pC(var1, var5);
         String var6 = (String)var5.get(var5.size() - 1);
         ayi var7 = new ayi(this, var6, var1, var2, var3);
         var8 = (ayi)this.pC(var1, var5, var7);
      }

      return var8;
   }

   @Override
   public Couple createVirtualTableDefinition(String var1, Long var2, Collection var3) {
      ayz var4 = new ayz(this, var1);
      avc var5 = new avc(var2);

      for (INativeMethodItem var7 : var3) {
         var4.pC(var7.getName(true));
         var5.pC(var7);
      }

      var4.pC();
      var5.pC();
      return new Couple(var4, var5);
   }

   @Override
   public void setClassVirtualTable(IClassType var1, IVirtualTableDefinition var2) {
      try (ACLock var3 = this.wS().a()) {
         ((ayi)var1).pC(var2);
      }
   }

   @Override
   public void setClassSuperTypes(IClassType var1, Collection var2, Collection var3) {
      try (ACLock var4 = this.wS().a()) {
         ((ayi)var1).pC(var2, var3);
      }
   }

   @Override
   public void completeClassTypeInitialization(IClassType var1) {
      ((ayi)var1).ys();
   }

   public ayf pC(String var1, INativeType var2) {
      ayf var4;
      try (ACLock var3 = this.wS().a()) {
         if (var2 == null) {
            throw new NullPointerException();
         }

         var4 = this.A(var1, var2);
      }

      return var4;
   }

   @Override
   public IAliasType createAlias(String var1, String var2) {
      ayf var5;
      try (ACLock var3 = this.wS().a()) {
         aye var4 = this.UT(var2);
         if (var4 == null) {
            throw new IllegalArgumentException("Unknown type: " + var2);
         }

         var5 = this.pC(var1, var4);
      }

      return var5;
   }

   public ayf A(String var1, INativeType var2) {
      ayf var7;
      try (ACLock var3 = this.wS().a()) {
         ArrayList var4 = new ArrayList();
         var1 = this.pC(var1, var4, true);
         String var5 = (String)var4.get(var4.size() - 1);
         if (var2 == null) {
            return new ayf(this, var5, var1, null);
         }

         ayf var6 = new ayf(this, var5, var1, (aye)var2);
         var7 = (ayf)this.pC(var1, var4, var6);
      }

      return var7;
   }

   void pC(ayf var1, aye var2) {
      this.wS().verifyLocked();
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1.E() != null) {
         throw new IllegalStateException();
      } else {
         var1.pC(var2, false);
         ArrayList var3 = new ArrayList();
         String var4 = this.pC(var1.getSignature(false), var3, true);
         this.pC(var4, var3, var1);
      }
   }

   public aye UT() {
      return this.UT("void");
   }

   public ayt E() {
      return this.LM;
   }

   public ayt pC(INativeType var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return this.pC((aye)var1, var2);
      }
   }

   @Override
   public IReferenceType createReference(INativeType var1) {
      return this.pC(var1, 1);
   }

   public ayt pC(aye param1, int param2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:359)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:504)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.insertSemaphore(FinallyProcessor.java:350)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:99)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:178)
      //
      // Bytecode:
      // 00: aload 1
      // 01: ifnonnull 0f
      // 04: new com/pnfsoftware/jebglobal/ayt
      // 07: dup
      // 08: aload 0
      // 09: aconst_null
      // 0a: iload 2
      // 0b: invokespecial com/pnfsoftware/jebglobal/ayt.<init> (Lcom/pnfsoftware/jebglobal/ayy;Lcom/pnfsoftware/jebglobal/aye;I)V
      // 0e: areturn
      // 0f: aload 0
      // 10: getfield com/pnfsoftware/jebglobal/ayy.sY Ljava/util/IdentityHashMap;
      // 13: dup
      // 14: astore 4
      // 16: monitorenter
      // 17: aload 0
      // 18: getfield com/pnfsoftware/jebglobal/ayy.sY Ljava/util/IdentityHashMap;
      // 1b: aload 1
      // 1c: invokevirtual java/util/IdentityHashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 1f: checkcast java/util/List
      // 22: astore 3
      // 23: aload 3
      // 24: ifnonnull 39
      // 27: new java/util/ArrayList
      // 2a: dup
      // 2b: invokespecial java/util/ArrayList.<init> ()V
      // 2e: astore 3
      // 2f: aload 0
      // 30: getfield com/pnfsoftware/jebglobal/ayy.sY Ljava/util/IdentityHashMap;
      // 33: aload 1
      // 34: aload 3
      // 35: invokevirtual java/util/IdentityHashMap.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      // 38: pop
      // 39: aload 3
      // 3a: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 3f: astore 5
      // 41: aload 5
      // 43: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 48: ifeq 69
      // 4b: aload 5
      // 4d: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 52: checkcast com/pnfsoftware/jebglobal/ayt
      // 55: astore 6
      // 57: aload 6
      // 59: invokevirtual com/pnfsoftware/jebglobal/ayt.getReferenceCount ()I
      // 5c: iload 2
      // 5d: if_icmpne 66
      // 60: aload 6
      // 62: aload 4
      // 64: monitorexit
      // 65: areturn
      // 66: goto 41
      // 69: aload 4
      // 6b: monitorexit
      // 6c: goto 77
      // 6f: astore 7
      // 71: aload 4
      // 73: monitorexit
      // 74: aload 7
      // 76: athrow
      // 77: aload 0
      // 78: invokevirtual com/pnfsoftware/jebglobal/ayy.wS ()Lcom/pnfsoftware/jeb/core/units/IUnitLock;
      // 7b: invokeinterface com/pnfsoftware/jeb/core/units/IUnitLock.a ()Lcom/pnfsoftware/jeb/util/concurrent/ACLock; 1
      // 80: astore 4
      // 82: new com/pnfsoftware/jebglobal/ayt
      // 85: dup
      // 86: aload 0
      // 87: aload 1
      // 88: iload 2
      // 89: invokespecial com/pnfsoftware/jebglobal/ayt.<init> (Lcom/pnfsoftware/jebglobal/ayy;Lcom/pnfsoftware/jebglobal/aye;I)V
      // 8c: astore 5
      // 8e: aload 3
      // 8f: aload 5
      // 91: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 96: pop
      // 97: aload 0
      // 98: aload 5
      // 9a: invokevirtual com/pnfsoftware/jebglobal/ayy.A (Lcom/pnfsoftware/jebglobal/aye;)Lcom/pnfsoftware/jebglobal/aye;
      // 9d: checkcast com/pnfsoftware/jebglobal/ayt
      // a0: astore 6
      // a2: aload 4
      // a4: ifnull ae
      // a7: aload 4
      // a9: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // ae: aload 6
      // b0: areturn
      // b1: astore 5
      // b3: aload 4
      // b5: ifnull cb
      // b8: aload 4
      // ba: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // bf: goto cb
      // c2: astore 6
      // c4: aload 5
      // c6: aload 6
      // c8: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // cb: aload 5
      // cd: athrow
   }

   void pC(ayt var1, aye var2) {
      this.wS().verifyLocked();
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1.E() != null) {
         throw new IllegalStateException();
      } else {
         var1.pC(var2, false);
         synchronized (this.sY) {
            Object var4 = (List)this.sY.get(var2);
            if (var4 == null) {
               var4 = new ArrayList();
               this.sY.put(var2, var4);
            }

            var4.add(var1);
         }

         this.A((aye)var1);
      }
   }

   @Override
   public IArrayType createArray(String var1, int var2) {
      INativeType var3 = TypeUtil.buildQuickType(this, var1);
      return var3 == null ? null : this.A(var3, var2);
   }

   public ayg A(INativeType var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         return this.A((aye)var1, var2);
      }
   }

   public ayg pC(INativeType var1, int var2, boolean var3) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var3) {
         ayg var6;
         try (ACLock var4 = this.wS().a()) {
            ayg var5 = new ayg(this, (aye)var1, var2);
            var5.A(128, false);
            var6 = (ayg)this.A((aye)var5);
         }

         return var6;
      } else {
         return this.A((aye)var1, var2);
      }
   }

   public ayg A(aye param1, int param2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:359)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:504)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1057)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.insertSemaphore(FinallyProcessor.java:350)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:99)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:178)
      //
      // Bytecode:
      // 00: aload 1
      // 01: ifnonnull 0f
      // 04: new com/pnfsoftware/jebglobal/ayg
      // 07: dup
      // 08: aload 0
      // 09: aconst_null
      // 0a: iload 2
      // 0b: invokespecial com/pnfsoftware/jebglobal/ayg.<init> (Lcom/pnfsoftware/jebglobal/ayy;Lcom/pnfsoftware/jebglobal/aye;I)V
      // 0e: areturn
      // 0f: aload 0
      // 10: getfield com/pnfsoftware/jebglobal/ayy.E Ljava/util/IdentityHashMap;
      // 13: dup
      // 14: astore 4
      // 16: monitorenter
      // 17: aload 0
      // 18: getfield com/pnfsoftware/jebglobal/ayy.E Ljava/util/IdentityHashMap;
      // 1b: aload 1
      // 1c: invokevirtual java/util/IdentityHashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 1f: checkcast java/util/List
      // 22: astore 3
      // 23: aload 3
      // 24: ifnonnull 39
      // 27: new java/util/ArrayList
      // 2a: dup
      // 2b: invokespecial java/util/ArrayList.<init> ()V
      // 2e: astore 3
      // 2f: aload 0
      // 30: getfield com/pnfsoftware/jebglobal/ayy.E Ljava/util/IdentityHashMap;
      // 33: aload 1
      // 34: aload 3
      // 35: invokevirtual java/util/IdentityHashMap.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      // 38: pop
      // 39: aload 3
      // 3a: invokeinterface java/util/List.iterator ()Ljava/util/Iterator; 1
      // 3f: astore 5
      // 41: aload 5
      // 43: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 48: ifeq 69
      // 4b: aload 5
      // 4d: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 52: checkcast com/pnfsoftware/jebglobal/ayg
      // 55: astore 6
      // 57: aload 6
      // 59: invokevirtual com/pnfsoftware/jebglobal/ayg.getElementCount ()I
      // 5c: iload 2
      // 5d: if_icmpne 66
      // 60: aload 6
      // 62: aload 4
      // 64: monitorexit
      // 65: areturn
      // 66: goto 41
      // 69: aload 4
      // 6b: monitorexit
      // 6c: goto 77
      // 6f: astore 7
      // 71: aload 4
      // 73: monitorexit
      // 74: aload 7
      // 76: athrow
      // 77: aload 0
      // 78: invokevirtual com/pnfsoftware/jebglobal/ayy.wS ()Lcom/pnfsoftware/jeb/core/units/IUnitLock;
      // 7b: invokeinterface com/pnfsoftware/jeb/core/units/IUnitLock.a ()Lcom/pnfsoftware/jeb/util/concurrent/ACLock; 1
      // 80: astore 4
      // 82: new com/pnfsoftware/jebglobal/ayg
      // 85: dup
      // 86: aload 0
      // 87: aload 1
      // 88: iload 2
      // 89: invokespecial com/pnfsoftware/jebglobal/ayg.<init> (Lcom/pnfsoftware/jebglobal/ayy;Lcom/pnfsoftware/jebglobal/aye;I)V
      // 8c: astore 5
      // 8e: aload 3
      // 8f: aload 5
      // 91: invokeinterface java/util/List.add (Ljava/lang/Object;)Z 2
      // 96: pop
      // 97: aload 0
      // 98: aload 5
      // 9a: invokevirtual com/pnfsoftware/jebglobal/ayy.A (Lcom/pnfsoftware/jebglobal/aye;)Lcom/pnfsoftware/jebglobal/aye;
      // 9d: checkcast com/pnfsoftware/jebglobal/ayg
      // a0: astore 6
      // a2: aload 4
      // a4: ifnull ae
      // a7: aload 4
      // a9: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // ae: aload 6
      // b0: areturn
      // b1: astore 5
      // b3: aload 4
      // b5: ifnull cb
      // b8: aload 4
      // ba: invokeinterface com/pnfsoftware/jeb/util/concurrent/ACLock.close ()V 1
      // bf: goto cb
      // c2: astore 6
      // c4: aload 5
      // c6: aload 6
      // c8: invokevirtual java/lang/Throwable.addSuppressed (Ljava/lang/Throwable;)V
      // cb: aload 5
      // cd: athrow
   }

   void pC(ayg var1, aye var2) {
      this.wS().verifyLocked();
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else if (var1.E() != null) {
         throw new IllegalStateException();
      } else {
         var1.pC(var2, false);
         synchronized (this.E) {
            Object var4 = (List)this.E.get(var2);
            if (var4 == null) {
               var4 = new ArrayList();
               this.E.put(var2, var4);
            }

            var4.add(var1);
         }

         this.A((aye)var1);
      }
   }

   public ays pC(ICallingConvention var1, List var2, List var3, Collection var4) {
      ArrayList var5 = new ArrayList();
      if (var2 != null) {
         for (INativeType var7 : var2) {
            var5.add((aye)var7);
         }
      }

      ArrayList var9 = new ArrayList();
      if (var3 != null) {
         for (INativeType var8 : var3) {
            var9.add((aye)var8);
         }
      }

      return this.pC(var5, var9, var1, var4);
   }

   public ays pC(ICallingConvention var1, INativeType var2, List var3, Collection var4) {
      return this.pC(var1, var2 == null ? null : Arrays.asList(var2), var3, var4);
   }

   public ays pC(INativeType var1, List var2) {
      return this.pC(null, var1, var2, null);
   }

   public ays sY() {
      return this.pC(null, null, null, null);
   }

   public ays pC(List var1, List var2, ICallingConvention var3, Collection var4) {
      ays var7;
      try (ACLock var5 = this.wS().a()) {
         ays var6 = new ays(this, var3, var1, var2, var4);
         this.ys.add(var6);
         var7 = (ays)this.A((aye)var6);
      }

      return var7;
   }

   public ays pC(String var1, List var2, List var3, Collection var4) {
      ICallingConvention var5 = null;
      if (var1 != null) {
         var5 = this.A().getConvention(var1);
      }

      ArrayList var6 = new ArrayList();
      if (var2 != null) {
         for (String var8 : var2) {
            var6.add((aye)this.gp(var8));
         }
      }

      ArrayList var10 = new ArrayList();
      if (var3 != null) {
         for (String var9 : var3) {
            var10.add((aye)this.gp(var9));
         }
      }

      return this.pC(var6, var10, var5, var4);
   }

   private INativeType gp(String var1) {
      return TypeUtil.buildQuickType(this, var1);
   }

   public aye UT(String var1) {
      return this.pC(var1, true, true);
   }

   public aye pC(String var1, boolean var2) {
      return this.pC(var1, var2, true);
   }

   public aye pC(String var1, boolean var2, boolean var3) {
      var1 = TypeUtil.preNormalizeSignature(var1);
      ayq var4 = this.A.pC(var1);
      if (var4 != null) {
         return var4;
      } else {
         aye var14 = (aye)this.UT.get(var1);
         if (var14 != null) {
            return var14;
         } else {
            ArrayList var5 = new ArrayList();
            int[] var6 = new int[2];
            var1 = TypeUtil.normalizeSignature(true, var1, var5, var6);
            if (var1 == null) {
               return null;
            } else {
               String var7 = TypeUtil.buildFullyQualifiedTypeNameFromElements(var5);
               Object var15 = this.A.pC(var7);
               if (var15 == null) {
                  var15 = (aye)this.UT.get(var7);
               }

               if (var15 != null) {
                  return this.pC((aye)var15, var6);
               } else {
                  if (var3) {
                     ayo var8 = this.oT;

                     for (int var9 = 0; var9 < var5.size() - 1; var9++) {
                        ayo var10 = var8.pC((String)var5.get(var9), true);
                        if (var10 == null) {
                           var8 = null;
                           break;
                        }

                        var8 = var10;
                     }

                     if (var8 != null) {
                        String var20 = (String)var5.get(var5.size() - 1);

                        for (aye var11 : var8.pC(aye.class)) {
                           if (Strings.equals(var20, var11.getName(true))) {
                              return this.pC(var11, var6);
                           }
                        }
                     }
                  }

                  if (var2 && this.sO != null) {
                     var15 = (aye)this.sO.findTypeBySignature(var1, this.WR);
                     if (var15 != null) {
                        aye var19 = this.pC((aye)var15);
                        if (var19 == null) {
                           pC.error("Import() failed for type: %s", var15);
                           return null;
                        }

                        return var19;
                     }

                     var15 = (aye)this.sO.findTypeBySignature(var7, this.WR);
                     if (var15 != null) {
                        aye var18 = this.pC((aye)var15);
                        if (var18 == null) {
                           pC.error("Import() failed for type: %s", var15);
                           return null;
                        }

                        return this.pC(var18, var6);
                     }
                  }

                  return "bool".equals(var7) ? this.A.pC("_Bool") : null;
               }
            }
         }
      }
   }

   private aye pC(aye var1, int[] var2) {
      Object var3 = var1;
      if (var2[0] > 0) {
         var3 = this.pC((INativeType)var1, var2[0]);
      }

      if (var2[1] >= 0) {
         var3 = this.A((INativeType)var3, var2[1]);
      }

      return (aye)var3;
   }

   public aye E(String var1) {
      aye var2 = this.UT(var1);
      if (var2 == null) {
         throw new JebRuntimeException(Strings.ff("Type not found: \"%s\"", var1));
      } else {
         return var2;
      }
   }

   public aye pC(int var1, boolean var2) {
      ayq var3 = this.A.A(var1, var2);
      return (aye)TypeUtil.findShorterForm(var3);
   }

   public aye A(int var1, boolean var2) {
      ayq var3 = this.A.pC(var1, var2);
      return (aye)TypeUtil.findShorterForm(var3);
   }

   public aye A(int var1) {
      ayq var2 = this.A.pC(var1);
      return (aye)TypeUtil.findShorterForm(var2);
   }

   @Override
   public Collection getTypes() {
      return this.UT.values();
   }

   @Override
   public Collection getTypes(ISimpleFilter var1) {
      ArrayList var2 = new ArrayList();

      for (INativeType var4 : this.UT.values()) {
         if (var1 == null || var1.check(var4)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   private Iterator pC(Class var1) {
      return new ayy.Av(var1);
   }

   public Iterator ys() {
      return this.pC(aym.class);
   }

   @Override
   public Collection getPrototypes() {
      return this.ys;
   }

   @Override
   public boolean deleteType(INativeType var1) {
      boolean var4;
      try (ACLock var2 = this.wS().a()) {
         aye var3 = (aye)var1;
         if (var3.isDisposed()) {
            return true;
         }

         if (var3 instanceof ayq) {
            pC.warn("Attempt to dispose primitive type \"%s\" was denied", var1.getName(true));
            return false;
         }

         var3.xC();
         var4 = true;
      }

      return var4;
   }

   void pC(INativeType var1) {
      this.wS().verifyLocked();
      if (var1 instanceof ayq) {
         throw new RuntimeException();
      } else {
         if (var1 instanceof ayg) {
            aye var2 = ((ayg)var1).E();
            Assert.a(var2 != null);
            synchronized (this.E) {
               List var4 = (List)this.E.get(var2);
               if (var4 != null) {
                  boolean var5 = var4.remove(var1);
                  Assert.a(var5, "Expected array type was not found: " + var1);
                  if (var4.isEmpty()) {
                     this.E.remove(var2);
                  }
               }
            }
         } else if (var1 instanceof ayt) {
            aye var12 = ((ayt)var1).E();
            Assert.a(var12 != null);
            synchronized (this.sY) {
               List var17 = (List)this.sY.get(var12);
               if (var17 != null) {
                  boolean var19 = var17.remove(var1);
                  Assert.a(var19, "Expected reference type was not found: " + var1);
                  if (var17.isEmpty()) {
                     this.sY.remove(var12);
                  }
               }
            }
         } else if (var1 instanceof ays) {
            boolean var13 = this.ys.remove(var1);
            Assert.a(var13, "Expected prototype was not found: " + var1);
         } else {
            String var14 = var1.getSignature(false);
            ayx var16 = (ayx)this.UT.remove(var14);
            Assert.a(var16 == var1, "Expected true type was not found: " + var1);
            synchronized (this.gp) {
               if (this.gp.containsKey(var1)) {
                  boolean var20 = this.UT((aye)var1);
                  Assert.a(var20, "Could not delete hierarchy node for type: " + var1);
               }
            }
         }

         this.vP.remove(var1.getIndex());
      }
   }

   @Override
   public String toString() {
      return Strings.ff("TYPEMAN(%d)", this.UT.size());
   }

   public ayo ld() {
      return this.oT;
   }

   private ayo wS(auo var1) {
      Assert.a(var1 != null);
      synchronized (this.gp) {
         ayo var3 = (ayo)this.gp.get(var1);
         if (var3 == null) {
            if (var1.isDisposed()) {
               throw new IllegalStateException("The item is disposed, refusing to create a node for it");
            }

            var3 = new ayo(var1);
            this.gp.put(var1, var3);
         }

         return var3;
      }
   }

   public ayo pC(auo var1) {
      return var1 == null ? this.oT : this.wS(var1);
   }

   public auo A(auo var1) {
      ayo var2 = this.pC(var1).pC();
      return var2 != null && var2 != this.oT ? var2.A() : null;
   }

   ayo pC(ayo var1, String var2) {
      ayo var6;
      try (ACLock var3 = this.wS().a()) {
         if (var1 == null && var2 != null) {
            var1 = this.oT;
         } else if (var1 == null && var2 != null) {
            throw new IllegalArgumentException("Illegal root node");
         }

         if (var1 != null) {
            ayo var4 = var1.pC(var2);
            if (var4 != null) {
               return var4;
            }
         }

         ayp var9 = new ayp(this, var2);
         var9.pC(-8286623314361712640L | this.Er & 4294967295L);
         this.FE.put(this.Er, var9);
         this.Er++;
         this.ld.add(var9);
         ayo var5 = this.wS(var9);
         var5.pC(var1);
         var5.pC(true);
         var6 = var5;
      }

      return var6;
   }

   public ayo sY(String var1) {
      ayo var4;
      try (ACLock var2 = this.wS().a()) {
         List var3 = TypeUtil.splitCppName(var1);
         if (var3.isEmpty()) {
            return null;
         }

         var4 = this.pC(var3);
      }

      return var4;
   }

   public ayo pC(List var1) {
      ayo var9;
      try (ACLock var2 = this.wS().a()) {
         ayo var3 = this.oT;

         for (String var5 : var1) {
            ayo var6 = var3.pC(var5);
            if (var6 == null) {
               var6 = this.pC(var3, var5);
            }

            var3 = var6;
         }

         var9 = var3;
      }

      return var9;
   }

   @Override
   public List getPackages() {
      return Collections.unmodifiableList(this.ld);
   }

   public ayp kS(auo var1) {
      ayo var2 = this.pC(var1);
      if (var2 == null) {
         return null;
      } else {
         for (ayo var3 = var2.pC(); var3 != null; var3 = var3.pC()) {
            auo var4 = var3.A();
            if (var4 instanceof ayp) {
               return (ayp)var4;
            }
         }

         return null;
      }
   }

   public ayp pC(INativeItem var1) {
      return var1 == null ? null : this.kS((auo)var1);
   }

   public ayp pC(IPackage var1, String var2) {
      ayp var5;
      try (ACLock var3 = this.wS().a()) {
         ayo var4 = this.pC((auo)((ayp)var1));
         var4 = this.pC(var4, var2);
         if (var4 == null) {
            return null;
         }

         var5 = (ayp)var4.A();
      }

      return var5;
   }

   public ayp ys(String var1) {
      ayp var4;
      try (ACLock var2 = this.wS().a()) {
         ayo var3 = this.sY(var1);
         if (var3 == null) {
            return null;
         }

         var4 = (ayp)var3.A();
      }

      return var4;
   }

   @Override
   public boolean moveToPackage(INativeItem var1, IPackage var2) {
      try (ACLock var3 = this.wS().a()) {
         ayp var4 = (ayp)var2;
         if (var1 instanceof auq var12) {
            this.pC((auo)var12).pC(null);
            this.pC((auo)var4).A(this.pC((auo)var12));
            ayi var16 = var12.sY();
            this.pC((auo)var16).pC(null);
            this.pC((auo)var4).A(this.pC((auo)var16));
            return true;
         }

         if (var1 instanceof auu var11) {
            if (var11.isVirtualMethod()) {
               pC.error("The method is a virtual method of a class and cannot be moved");
               return false;
            }

            this.pC((auo)var11).pC(null);
            this.pC((auo)var4).A(this.pC((auo)var11));
            return true;
         }

         if (var1 instanceof aur var10) {
            if (var10.E()) {
               pC.error("The field is an instance field of a class and cannot be moved");
               return false;
            }

            this.pC((auo)var10).pC(null);
            this.pC((auo)var4).A(this.pC((auo)var10));
            return true;
         }
      }

      boolean var5;
      return var5;
   }

   @Override
   public boolean deletePackage(IPackage var1) {
      boolean var3;
      try (ACLock var2 = this.wS().a()) {
         var3 = this.UT((ayp)var1);
      }

      return var3;
   }

   public auu pC(INativeItem var1, String var2, ays var3, aut var4) {
      auu var9;
      try (ACLock var5 = this.wS().a()) {
         auu var6 = new auu(this, var2, var3, var4);
         var6.wS(this.eP);
         var6.pC(-8718968878589280256L | this.eP & 4294967295L);
         this.UO.put(this.eP, var6);
         this.eP++;
         if (var1 != null) {
            this.pC(var6, var1);
         }

         ayo var7 = this.wS(var6);
         ayo var8 = var1 == null ? this.oT : this.wS((auo)var1);
         var7.pC(var8);
         var6.addListener(this);
         var9 = var6;
      }

      return var9;
   }

   public auu A(long var1) {
      if ((var1 & -72057594037927936L) != -8718968878589280256L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.kS(var3);
      }
   }

   public auu kS(int var1) {
      return (auu)this.UO.get(var1);
   }

   public Collection gp() {
      return this.UO.values();
   }

   public ayp kS(long var1) {
      if ((var1 & -72057594037927936L) != -8286623314361712640L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.wS(var3);
      }
   }

   public ayp wS(int var1) {
      return (ayp)this.FE.get(var1);
   }

   public aur pC(INativeItem var1, String var2, INativeType var3) {
      aur var6;
      try (ACLock var4 = this.wS().a()) {
         aur var5 = new aur(this, var2, (aye)var3);
         var6 = this.pC(var1, var5);
      }

      return var6;
   }

   public aur pC(INativeItem var1, INativeDataItem var2) {
      aur var5;
      try (ACLock var3 = this.wS().a()) {
         aur var4 = new aur(this, var2);
         var5 = this.pC(var1, var4);
      }

      return var5;
   }

   public aur pC(INativeItem var1, ayv var2, ayu var3) {
      aur var6;
      try (ACLock var4 = this.wS().a()) {
         aur var5 = new aur(this, var2, var3);
         var6 = this.pC(var1, var5);
      }

      return var6;
   }

   private aur pC(INativeItem var1, aur var2) {
      this.wS().verifyLocked();
      var2.wS(this.Sc);
      var2.pC(-8358680908399640576L | this.Sc & 4294967295L);
      this.ah.put(this.Sc, var2);
      this.Sc++;
      if (var1 != null) {
         this.pC(var2, var1);
      }

      ayo var3 = this.wS(var2);
      ayo var4 = var1 == null ? this.oT : this.wS((auo)var1);
      var3.pC(var4);
      var2.addListener(this);
      return var2;
   }

   public aur wS(long var1) {
      if ((var1 & -72057594037927936L) != -8358680908399640576L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.UT(var3);
      }
   }

   public aur UT(int var1) {
      return (aur)this.ah.get(var1);
   }

   public Collection oT() {
      return this.ah.values();
   }

   private auq pC(IPackage var1, ayi var2) {
      this.wS().verifyLocked();
      auq var3 = new auq(this, var2.A);
      var3.wS(this.xC);
      var3.pC(-8430738502437568512L | this.xC & 4294967295L);
      this.ED.put(this.xC, var3);
      this.xC++;
      if (var1 != null) {
         this.moveToPackage(var3, var1);
      }

      ayo var4 = this.wS(var3);
      ayo var5 = var1 == null ? this.oT : this.wS((ayp)var1);
      var4.pC(var5);
      var3.addListener(this);
      return var3;
   }

   public auq pC(IClassType var1) {
      return this.pC((ayi)var1);
   }

   public auq pC(ayi var1) {
      auq var4;
      try (ACLock var2 = this.wS().a()) {
         auq var3 = this.pC(null, var1);
         var3.pC(var1);
         var4 = var3;
      }

      return var4;
   }

   @Override
   public void setVirtualTableMethods(INativeClassItem var1, List var2) {
      try (ACLock var3 = this.wS().a()) {
         ((auq)var1).pC(var2);
      }
   }

   @Override
   public void addNonVirtualMethod(INativeClassItem var1, INativeMethodItem var2) {
      try (ACLock var3 = this.wS().a()) {
         ((auq)var1).pC((auu)var2);
         this.moveToClass(var2, var1);
      }
   }

   @Override
   public void addStaticMethod(INativeClassItem var1, INativeMethodItem var2) {
      try (ACLock var3 = this.wS().a()) {
         ((auq)var1).A((auu)var2);
         this.moveToClass(var2, var1);
      }
   }

   @Override
   public void completeClassInitialization(INativeClassItem var1) {
      try (ACLock var2 = this.wS().a()) {
         ((auq)var1).UT();
      }
   }

   public auq UT(long var1) {
      if ((var1 & -72057594037927936L) != -8430738502437568512L) {
         return null;
      } else {
         int var3 = (int)var1;
         return this.E(var3);
      }
   }

   public auq E(int var1) {
      return (auq)this.ED.get(var1);
   }

   public Collection fI() {
      return this.ED.values();
   }

   @Override
   public boolean moveToClass(INativeItem var1, INativeClassItem var2) {
      return this.pC(var1, (auq)var2);
   }

   public boolean pC(INativeItem var1, auq var2) {
      try (ACLock var3 = this.wS().a()) {
         if (var1 instanceof auq) {
            pC.error("Inner classes are not supported");
            return false;
         }

         if (var1 instanceof auu var9) {
            if (var9.isVirtualMethod()) {
               pC.error("The method is a virtual method of a class and cannot be moved");
               return false;
            }

            this.pC((auo)var9).pC(null);
            this.pC((auo)var2).A(this.pC((auo)var9));
            return true;
         }

         if (var1 instanceof aur var8) {
            if (var8.E()) {
               pC.error("The field is an instance field of a class and cannot be moved");
               return false;
            }

            this.pC((auo)var8).pC(null);
            this.pC((auo)var2).A(this.pC((auo)var8));
            return true;
         }
      }

      boolean var4;
      return var4;
   }

   private boolean UT(auo var1) {
      this.wS().verifyLocked();
      ayo var2 = this.pC(var1);
      if (var2.hasChildren()) {
         return false;
      } else {
         this.pC(var2);
         synchronized (this.gp) {
            this.gp.remove(var1);
            return true;
         }
      }
   }

   private void pC(ayo var1) {
      this.wS().verifyLocked();
      ayo var2 = var1.pC();
      if (var2 != null) {
         var2.kS(var1);
      }
   }

   public INativeItem ld(String var1) {
      ArrayList var2 = new ArrayList();
      if (!TypeUtil.isValidFullyQualifiedName(var1, var2, null)) {
         return null;
      } else {
         ayo var3 = this.oT;

         for (String var5 : var2) {
            List var6 = var3.getChildren();
            var3 = this.pC(var6, var5, false);
            if (var3 == null) {
               var3 = this.pC(var6, var5, true);
               if (var3 == null) {
                  return null;
               }
            }
         }

         return var3.A();
      }
   }

   private ayo pC(List var1, String var2, boolean var3) {
      for (ayo var5 : var1) {
         auo var6 = var5.A();
         if (!(var6 instanceof aye) && Strings.equals(var6.getName(var3), var2)) {
            return var5;
         }
      }

      return null;
   }

   private boolean pC(INativeItem var1, INativeItem var2) {
      try (ACLock var3 = this.wS().a()) {
         if (var2 instanceof IPackage) {
            return this.moveToPackage(var1, (IPackage)var2);
         }

         if (var2 instanceof auq) {
            return this.pC(var1, (auq)var2);
         }
      }

      boolean var4;
      return var4;
   }

   public void pC(aur var1) {
      this.wS().verifyLocked();
      this.ah.remove(var1.getIndex());
      this.UT(var1);
   }

   public void pC(auu var1) {
      this.wS().verifyLocked();
      this.UO.remove(var1.getIndex());
      this.UT(var1);
   }

   public void pC(ayp var1) {
      this.wS().verifyLocked();
      this.FE.remove(var1.getIndex());
      this.UT(var1);
   }

   public void pC(auq var1) {
      this.wS().verifyLocked();
      this.ED.remove(var1.getIndex());
      this.UT(var1);
   }

   public aye pC(aye var1) {
      aye var5;
      try (ACLock var2 = this.wS().a()) {
         ayw var3 = new ayw(this);
         aye var4 = var3.pC(var1);
         this.Ab = this.Ab + var3.pC();
         var5 = var4;
      }

      return var5;
   }

   public auu pC(IMethodManager var1, auu var2) {
      if (var2.E() != null) {
         throw new IllegalArgumentException("Cannot import internal routine");
      } else if (var2.kS() == this) {
         return var2;
      } else {
         auu var6;
         try (ACLock var3 = this.wS().a()) {
            ays var4 = (ays)this.pC((aye)var2.UT());
            if (var4 == null) {
               pC.warn("Failed to import: %s", var2);
               return null;
            }

            auu var5 = (auu)var1.createMethodReference(var2.getName(true), var4, null);
            var5.setParameterNames(new ArrayList(var2.getParameterNames()));
            var6 = var5;
         }

         return var6;
      }
   }

   @Override
   public void verify(INativeType var1) {
      ITypeManager var2 = var1.getTypeManager();
      if (var2 != this) {
         throw new JebRuntimeException("Illegal type, not managed by the current type manager");
      }
   }

   public aye A(String var1, boolean var2, boolean var3) {
      if (var1 == null) {
         return null;
      } else {
         int var4 = 0;
         boolean var5 = true;

         while (var5) {
            var5 = false;
            var1 = var1.trim();
            if (var1.endsWith("*")) {
               var4++;
               var1 = var1.substring(0, var1.length() - 1);
               var5 = true;
            }
         }

         Object var6 = this.UT(var1);
         if (var6 == null) {
            if (var3) {
               var6 = this.wS(var1);
            } else {
               try (ACLock var7 = this.wS().a()) {
                  ArrayList var9 = new ArrayList();
                  String var8 = this.pC(var1, var9);
                  var6 = (aye)this.mv.get(var8);
                  if (var6 == null) {
                     var6 = new ayi(this, var8, var8, 1, 1);
                     this.mv.put(var8, var6);
                  }
               }
            }
         }

         while (var4 > 0) {
            var6 = ((aye)var6).UT();
            var4--;
         }

         return (aye)var6;
      }
   }

   public void WR() {
      for (Entry var2 : this.ED.entrySet()) {
         ((auq)var2.getValue()).dispose(false);
      }

      for (Entry var6 : this.UO.entrySet()) {
         ((auu)var6.getValue()).dispose(false);
      }

      for (int var5 = this.NS - 1; var5 >= 0; var5--) {
         try {
            aye var7 = (aye)this.vP.get(var5);
            if (var7 != null) {
               var7.dispose(false);
            }
         } catch (Exception var3) {
         }
      }

      this.A = null;
      this.wS = null;
      this.UT = null;
      this.E = null;
      this.sY = null;
      this.ys = null;
      this.ld = null;
      this.gp = null;
      this.oT = null;
      this.fI = null;
      this.WR = null;
      this.vP = null;
      this.ED = null;
      this.ah = null;
      this.UO = null;
      this.rl = null;
      this.z = null;
      this.Ek = null;
      this.FE = null;
      this.Aj = null;
      this.EX = null;
      this.LM = null;
   }

   class Av implements Iterator {
      Class pC;
      Iterator A;
      INativeType kS;

      Av(Class var2) {
         this.pC = var2;
         this.A = ayy.this.UT.values().iterator();
         this.A();
      }

      private void A() {
         while (this.A.hasNext()) {
            INativeType var1 = (INativeType)this.A.next();
            var1 = TypeUtil.getNonAlias(var1);
            if (this.pC.isInstance(var1)) {
               this.kS = var1;
               return;
            }
         }

         this.kS = null;
      }

      @Override
      public boolean hasNext() {
         return this.kS != null;
      }

      public INativeType pC() {
         if (this.kS == null) {
            throw new NoSuchElementException();
         } else {
            INativeType var1 = this.kS;
            this.A();
            return var1;
         }
      }
   }
}
