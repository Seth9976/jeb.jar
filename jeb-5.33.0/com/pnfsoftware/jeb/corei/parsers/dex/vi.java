package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.actions.ActionAutoRenameAllData;
import com.pnfsoftware.jeb.core.actions.ActionCommentData;
import com.pnfsoftware.jeb.core.actions.ActionContext;
import com.pnfsoftware.jeb.core.actions.ActionCreatePackageData;
import com.pnfsoftware.jeb.core.actions.ActionMoveToData;
import com.pnfsoftware.jeb.core.actions.ActionMoveToPackageData;
import com.pnfsoftware.jeb.core.actions.ActionOverridesData;
import com.pnfsoftware.jeb.core.actions.ActionRenameData;
import com.pnfsoftware.jeb.core.actions.ActionTypeHierarchyData;
import com.pnfsoftware.jeb.core.actions.ActionXrefsData;
import com.pnfsoftware.jeb.core.actions.IActionData;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebException;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.input.FileInput;
import com.pnfsoftware.jeb.core.input.FileInputRegionInformation;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.input.IInputLocation;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.PackageCoordinates;
import com.pnfsoftware.jeb.core.output.tree.ICodeNode;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IMetadataManager;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.MetadataManager;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.android.DexBulkItemRenamer;
import com.pnfsoftware.jeb.core.units.code.android.DexCommentManager;
import com.pnfsoftware.jeb.core.units.code.android.DexConstantLibrary;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexFile;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IOptimizedDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.render.IDexDisassemblyDocument;
import com.pnfsoftware.jeb.core.units.impl.Comment;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowser;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.logging.StatusLogDrip;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import com.pnfsoftware.jebglobal.Nj;
import com.pnfsoftware.jebglobal.baj;
import com.pnfsoftware.jebglobal.beg;
import com.pnfsoftware.jebglobal.beo;
import com.pnfsoftware.jebglobal.bev;
import com.pnfsoftware.jebglobal.bfg;
import com.pnfsoftware.jebglobal.bfo;
import com.pnfsoftware.jebglobal.bfq;
import com.pnfsoftware.jebglobal.bfr;
import com.pnfsoftware.jebglobal.bfs;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bfv;
import com.pnfsoftware.jebglobal.bfw;
import com.pnfsoftware.jebglobal.bfx;
import com.pnfsoftware.jebglobal.bfy;
import com.pnfsoftware.jebglobal.bfz;
import com.pnfsoftware.jebglobal.bga;
import com.pnfsoftware.jebglobal.bgd;
import com.pnfsoftware.jebglobal.bge;
import com.pnfsoftware.jebglobal.bgf;
import com.pnfsoftware.jebglobal.bgg;
import com.pnfsoftware.jebglobal.bgh;
import com.pnfsoftware.jebglobal.bgi;
import com.pnfsoftware.jebglobal.bgj;
import com.pnfsoftware.jebglobal.bgw;
import com.pnfsoftware.jebglobal.bgx;
import com.pnfsoftware.jebglobal.bgy;
import com.pnfsoftware.jebglobal.bgz;
import com.pnfsoftware.jebglobal.bhb;
import com.pnfsoftware.jebglobal.bhc;
import com.pnfsoftware.jebglobal.bhd;
import com.pnfsoftware.jebglobal.bhe;
import com.pnfsoftware.jebglobal.bhf;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Ser
@SerVersion(4)
public class vi extends AbstractBinaryUnit implements IDexUnit {
   private static final ILogger hK = GlobalLog.getLogger(vi.class);
   @SerId(1)
   private byte[] Er;
   @SerId(2)
   boolean pC;
   @SerId(3)
   boolean A;
   @SerId(4)
   boolean kS;
   @SerId(5)
   boolean wS;
   @SerId(6)
   boolean UT;
   @SerId(value = 10, deprecated = true)
   @Deprecated
   private Map FE = new HashMap();
   @SerId(11)
   private Map Aj = new HashMap();
   @SerId(12)
   private Map EX = new HashMap();
   @SerId(13)
   bgi E;
   @SerId(14)
   bgj sY;
   @SerId(15)
   bgh ys;
   @SerId(16)
   bga ld;
   @SerId(17)
   bgf gp;
   @SerId(18)
   bfq oT;
   @SerId(19)
   HashMap fI;
   @SerId(20)
   bO WR;
   @SerTransient
   private yt LM;
   @SerTransient
   private bhf mv;
   @SerId(31)
   private bev[] sO;
   @SerTransient
   private bgy os;
   @SerTransient
   private Set Cu;
   @SerTransient
   private boolean hZ;
   @SerId(32)
   private MetadataManager UW;
   @SerId(33)
   private Map PR = new HashMap();
   @SerId(34)
   private List cX = new ArrayList();
   @SerId(40)
   private List DQ = new ArrayList();
   @SerId(41)
   private Map ZN = new HashMap();
   @SerTransient
   private Map OB = new HashMap();
   @SerId(value = 42, version = 1)
   bfo NS;
   @SerId(value = 43, version = 1)
   bge vP;
   @SerId(value = 44, version = 1)
   private List pF;
   @SerId(value = 45, version = 2)
   long xC;
   @SerTransient
   private Integer Bc;
   @SerTransient
   private zp OI;
   @SerTransient
   private IUnitContribution Bf;
   @SerTransient
   private String Pe;
   @SerId(value = 46, version = 3)
   int ED;
   @SerId(value = 50, version = 4)
   vi.Av Sc;
   @SerTransient
   private Boolean ck;
   @SerTransient
   private Boolean RW;
   @SerId(value = 51, deprecated = true)
   @Deprecated
   private rQ e;
   @SerId(52)
   private DexCommentManager xM = new DexCommentManager(this);
   @SerId(53)
   DexConstantLibrary ah = new DexConstantLibrary(this);
   @SerTransient
   private volatile bhd kU;
   @SerTransient
   private volatile bgx Kq;
   @SerTransient
   private volatile bgw go;
   @SerTransient
   private volatile bgz JF;
   @SerTransient
   private volatile Integer Nq;
   @SerTransient
   public List eP = new ArrayList();
   @SerTransient
   public List UO = new ArrayList();
   @SerId(54)
   private Map pg = new HashMap();
   @SerId(55)
   private MultiMap gj = new MultiMap();
   @SerId(56)
   private Set ZD;
   @SerId(value = 57, deprecated = true)
   public Map Ab;
   @SerTransient
   StatusLogDrip rl;
   @SerTransient
   int z;
   @SerTransient
   int Ek;

   @SerCustomInitPostGraph
   private void EX() {
      if (this.ZN == null) {
         this.ZN = new HashMap();
      }

      if (this.OB == null) {
         this.OB = new HashMap();
      }

      if (this.pg == null) {
         this.pg = new HashMap();
      }

      if (this.e == null && this.FE != null) {
         this.e = new rQ(this);
         this.e.A.putAll(this.FE);
         this.FE = null;
      }

      if (this.xM == null && this.e != null) {
         this.xM = new DexCommentManager(this);

         for (Entry var2 : this.e.pC().entrySet()) {
            this.xM.setPrimary2((ICodeCoordinates)var2.getKey(), (String)var2.getValue(), 0, false);
         }

         for (Entry var8 : this.e.A().entrySet()) {
            for (rQ.Av var4 : (Collection)var8.getValue()) {
               MetaComment var5 = new MetaComment(var4.pC(), var4.A());
               this.xM.addMetaComment2((ICodeCoordinates)var8.getKey(), var5, false);
            }
         }

         this.e = null;
      }

      if (this.WR != null && this.WR.pC == null) {
         this.WR.pC = this;
      }

      if (this.ah == null) {
         this.ah = new DexConstantLibrary(this);
      }

      if (this.gj == null) {
         this.gj = new MultiMap();

         for (Entry var9 : this.EX.entrySet()) {
            if (var9.getValue() != null && var9.getKey() != null) {
               this.gj.put((String)var9.getValue(), ((IdentifierCoordinates)var9.getKey()).getMethodIndex());
            }
         }
      }
   }

   public vi(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "dex", var1, var3, var4, var5);
   }

   @Override
   public void onPropertyChange(String var1) {
      if (var1 != null && var1.endsWith(".ContextInfoDb")) {
         this.go = null;
      }

      if (var1 != null && var1.endsWith(".PreferredBaseForIntegers")) {
         this.Nq = null;
      }
   }

   public void pC(int var1) {
      this.getPropertyManager().setInteger("DalvikParserMode", var1);
      this.ED = var1;
   }

   public synchronized int pC() {
      if (this.Bc == null) {
         if (this.pF != null && !this.pF.isEmpty()) {
            this.Bc = ((KD)this.pF.get(0)).wS;
         } else {
            this.Bc = 0;
         }
      }

      return this.Bc;
   }

   @Override
   public int getCountOfDexEntries() {
      return this.pF.size();
   }

   @Override
   public List getDexEntries() {
      return Collections.unmodifiableList(this.pF);
   }

   @Override
   public IDexFile getDexEntry(int var1) {
      return (IDexFile)this.pF.get(var1);
   }

   public bhf A() {
      if (this.mv == null) {
         this.mv = new bhf(this);
      }

      return this.mv;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new p(this, 1L, S.L("Disassembly"), true), false);
      }

      return var1;
   }

   @Override
   public IDexDisassemblyDocument getDisassemblyDocument() {
      return new bfg(this);
   }

   @Override
   public String getDisassembly() {
      IDexDisassemblyDocument var1 = this.getDisassemblyDocument();

      String var2;
      try {
         var2 = var1.format();
      } finally {
         var1.dispose();
      }

      return var2;
   }

   public boolean kS() {
      if (this.ck == null) {
         this.ck = this.getPropertyManager().getBoolean("ProvideFriendlyCodeNodeLabels");
      }

      return this.ck;
   }

   public boolean wS() {
      if (this.RW == null) {
         this.RW = this.getPropertyManager().getBoolean("ProvideExtraInfoInCodeNodeLabels");
      }

      return this.RW;
   }

   public List UT() {
      return Collections.unmodifiableList(this.DQ);
   }

   public synchronized byte[] E() throws IOException {
      if (this.Er == null) {
         try (InputStream var1 = this.getInput().getStream()) {
            this.Er = IO.readInputStream(var1);
         }
      }

      return this.Er;
   }

   public bgi sY() {
      if (this.E == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.E;
      }
   }

   public String A(int var1) {
      return ((bfx)this.sY().pC(var1)).getValue();
   }

   public String pC(int var1, String var2) {
      bfx var3 = (bfx)this.sY().pC(var1);
      return var3 == null ? var2 : var3.getValue();
   }

   public String pC(int var1, String var2, boolean var3) {
      bfx var4 = (bfx)this.sY().pC(var1);
      String var5 = var4 == null ? var2 : var4.getValue();
      return var3 ? Formatter.escapeString(var5) : var5;
   }

   @Override
   public List getStrings() {
      return this.sY().wS();
   }

   public bfx kS(int var1) {
      return (bfx)this.sY().pC(var1);
   }

   public bfx pC(String var1) {
      return (bfx)this.sY().kS(var1);
   }

   @Override
   public int findStringIndex(String var1) {
      bfx var2 = (bfx)this.sY().kS(var1);
      return var2 == null ? -1 : var2.getIndex();
   }

   @Override
   public int getStringCount() {
      return this.sY().pC();
   }

   public Long pC(long var1) {
      return (Long)this.fI.get(var1);
   }

   public Long A(long var1) {
      synchronized (this) {
         this.fI.put(var1, var1);
      }

      return (Long)this.fI.get(var1);
   }

   @Override
   public DexConstantLibrary getConstantsLibrary() {
      return this.ah;
   }

   public bO ys() {
      return this.WR;
   }

   @Override
   public int getBadTypeCount() {
      return this.ld().ld();
   }

   @Override
   public List getTypes() {
      return this.ld().wS();
   }

   public bgj ld() {
      if (this.sY == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.sY;
      }
   }

   public bfy wS(int var1) {
      return (bfy)this.ld().pC(var1);
   }

   public bfy A(String var1) {
      return this.ld().sY(var1);
   }

   public String pC(int var1, boolean var2) {
      return this.ld().pC(var1, var2, true);
   }

   public String pC(int var1, boolean var2, boolean var3) {
      return this.ld().pC(var1, var2, var3);
   }

   public String pC(int var1, boolean var2, boolean var3, boolean var4) {
      return this.ld().pC(var1, var2, var3, var4);
   }

   public bgh gp() {
      if (this.ys == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.ys;
      }
   }

   @Override
   public List getPrototypes() {
      return this.gp().wS();
   }

   public bfw UT(int var1) {
      return (bfw)this.gp().pC(var1);
   }

   public bga oT() {
      if (this.ld == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.ld;
      }
   }

   public bft E(int var1) {
      return (bft)this.oT().pC(var1);
   }

   @Override
   public List getFields() {
      return this.oT().wS();
   }

   public bft kS(String var1) {
      return this.oT().pC(var1);
   }

   @Override
   public IDexValue getStaticFieldInitializer(int var1) {
      bft var2 = this.E(var1);
      if (var2 == null) {
         return null;
      } else if (!var2.isInternal()) {
         return null;
      } else {
         bfz var3 = var2.A();
         if (var3 != null && var3.isStatic()) {
            bfs var4 = this.WR().pC(var2.getClassTypeSignature(false));
            if (var4 != null && var4.sY() != null) {
               beg[] var5 = var4.E();
               int var6 = 0;

               for (bfz var8 : var4.sY().getStaticFields()) {
                  if (var6 >= var5.length) {
                     return null;
                  }

                  if (var8 == var3) {
                     return var5[var6];
                  }

                  var6++;
               }

               throw new JebRuntimeException("Field not found: index=" + var1);
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public bgf fI() {
      if (this.gp == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.gp;
      }
   }

   public bfu sY(int var1) {
      return (bfu)this.fI().pC(var1);
   }

   @Override
   public List getMethods() {
      return this.fI().wS();
   }

   public bfu wS(String var1) {
      return this.fI().pC(var1);
   }

   public synchronized bfx UT(String var1) {
      return this.pC(var1, true);
   }

   public synchronized bfx pC(String var1, boolean var2) {
      bfx var3 = (bfx)this.sY().kS(var1);
      return var3 != null ? var3 : this.sY().pC(var1, var2, false);
   }

   public synchronized bfy E(String var1) {
      bfy var2 = (bfy)this.ld().kS(var1);
      if (var2 != null) {
         return var2;
      } else {
         this.pC(var1, false);
         var2 = this.ld().UT(var1);
         this.ld().sY();
         return var2;
      }
   }

   public synchronized bfw sY(String var1) {
      bfw var2 = (bfw)this.gp().kS(var1);
      if (var2 != null) {
         return var2;
      } else {
         String[] var3 = new String[1];
         List var4 = bfw.pC(var1, var3);
         if (var4 == null) {
            return null;
         } else {
            int var5 = this.pC(var3[0], false).getIndex();
            int var6 = -1;
            int[] var7 = new int[var4.size() - 1];
            int var8 = 0;

            for (String var10 : var4) {
               bfy var11 = this.E(var10);
               if (var11 == null) {
                  return null;
               }

               int var12 = var11.getIndex();
               if (var8 == 0) {
                  var6 = var12;
               } else {
                  var7[var8 - 1] = var12;
               }

               var8++;
            }

            return this.gp().pC(var5, var6, var7);
         }
      }
   }

   public synchronized bfu ys(String var1) {
      bfu var2 = (bfu)this.fI().kS(var1);
      if (var2 != null) {
         return var2;
      } else {
         String[] var3 = var1.split("->");
         if (var3.length != 2) {
            return null;
         } else {
            int var4 = var3[1].indexOf(40);
            if (var4 < 0) {
               return null;
            } else {
               String var5 = var3[0];
               String var6 = var3[1].substring(0, var4);
               String var7 = var3[1].substring(var4);
               return this.kS(var5, var6, var7);
            }
         }
      }
   }

   public synchronized bfu pC(String var1, String var2, String var3) {
      String var4 = var1 + "->" + var2 + var3;
      bfu var5 = (bfu)this.fI().kS(var4);
      return var5 != null ? var5 : this.kS(var1, var2, var3);
   }

   private bfu kS(String var1, String var2, String var3) {
      bfy var4 = this.E(var1);
      if (var4 == null) {
         return null;
      } else {
         int var5 = this.pC(var2, false).getIndex();
         bfw var6 = this.sY(var3);
         return var6 == null ? null : this.fI().pC(var4.getIndex(), var6.pC(), var5);
      }
   }

   public synchronized bft ld(String var1) {
      bft var2 = (bft)this.oT().kS(var1);
      if (var2 != null) {
         return var2;
      } else {
         String[] var3 = var1.split("->");
         if (var3.length != 2) {
            return null;
         } else {
            int var4 = var3[1].indexOf(58);
            if (var4 < 0) {
               return null;
            } else {
               String var5 = var3[0];
               String var6 = var3[1].substring(0, var4);
               String var7 = var3[1].substring(var4 + 1);
               return this.wS(var5, var6, var7);
            }
         }
      }
   }

   public synchronized bft A(String var1, String var2, String var3) {
      String var4 = var1 + "->" + var2 + ":" + var3;
      bft var5 = (bft)this.oT().kS(var4);
      return var5 != null ? var5 : this.wS(var1, var2, var3);
   }

   private bft wS(String var1, String var2, String var3) {
      bfy var4 = this.E(var1);
      if (var4 == null) {
         return null;
      } else {
         int var5 = this.pC(var2, false).getIndex();
         bfy var6 = this.E(var3);
         return var6 == null ? null : this.oT().pC(var4.getIndex(), var6.getIndex(), var5);
      }
   }

   public bfq WR() {
      if (this.oT == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.oT;
      }
   }

   public bfs ys(int var1) {
      return (bfs)this.WR().pC(var1);
   }

   @Override
   public List getClasses() {
      return this.WR().wS();
   }

   public bfs gp(String var1) {
      return this.WR().pC(var1);
   }

   bfo NS() {
      if (this.NS == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.NS;
      }
   }

   public bfr ld(int var1) {
      return (bfr)this.NS().pC(var1);
   }

   @Override
   public List getCallSites() {
      return this.NS().wS();
   }

   bge vP() {
      if (this.vP == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.vP;
      }
   }

   public bfv gp(int var1) {
      return (bfv)this.vP().pC(var1);
   }

   @Override
   public List getMethodHandles() {
      return this.vP().wS();
   }

   @Override
   public List getPackages() {
      return this.ld().UT();
   }

   public qt oT(String var1) {
      return this.ld().wS(var1);
   }

   public qt oT(int var1) {
      return this.ld().A(var1);
   }

   public qt fI(String var1) {
      HE var2 = this.ld().pC(var1);
      return var2 == null ? null : (qt)var2.kS();
   }

   @Override
   public boolean moveTo(IDexItem var1, IDexItem var2, boolean var3, boolean var4) {
      byte var5 = 0;
      if (var3) {
         var5 |= 1;
      }

      if (var4) {
         var5 |= 2;
      }

      return this.ld().pC((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var1, (com.pnfsoftware.jeb.corei.parsers.dex.Sv)var2, var5, true, true);
   }

   public yt xC() {
      if (this.LM == null) {
         this.LM = new yt(this);
      }

      return this.LM;
   }

   public bfu fI(int var1) {
      int var2 = 0;
      int var3 = this.sO.length;
      int var4 = 0;

      while (var3 > var2) {
         int var5 = var2 + (var3 - var2) / 2;
         bev var6 = this.sO[var5];
         int var7 = var6.getInstructionsOffset();
         if (var1 < var7) {
            var3 = var5;
         } else {
            if (var1 <= var7) {
               return (bfu)this.fI().pC(var6.pC());
            }

            var2 = var5 + 1;
            var4 = var5;
         }
      }

      if (var4 < this.sO.length) {
         bev var8 = this.sO[var4];
         if (var1 < var8.getInstructionsOffset() + var8.getInstructionsSize()) {
            return (bfu)this.fI().pC(var8.pC());
         }
      }

      return null;
   }

   public void pC(IInput var1) {
      this.DQ.add(var1);
   }

   public boolean ED() {
      return !this.DQ.isEmpty();
   }

   @Override
   protected boolean processInternal() {
      this.pC = this.getPropertyManager().getBoolean("VerifyVersion");
      this.A = this.getPropertyManager().getBoolean("VerifyHashes");
      this.kS = this.getPropertyManager().getBoolean("VerifyAccessFlags");
      this.wS = this.getPropertyManager().getBoolean("ParseExtendedOpcodes");
      this.UT = this.getPropertyManager().getBoolean("ParseOptimizedOpcodes");
      this.ED = this.getPropertyManager().getInteger("DalvikParserMode");
      if (this.ED >= 50 && this.getParent() instanceof IOptimizedDexUnit) {
         this.pC(0);
      } else if (this.ED < 50 && this.getParent() instanceof IUnit && Strings.equalsIgnoreCase("oat", ((IUnit)this.getParent()).getFormatType())) {
         this.pC(50);
      }

      this.Sc = new vi.Av();
      int var1 = 0;
      ArrayList var2 = new ArrayList();

      byte[] var3;
      try {
         var3 = this.E();
      } catch (IOException var15) {
         return false;
      }

      int var4 = 0;

      for (int var5 = 0; var4 < var3.length; var5++) {
         KD var6 = new KD(this, var3, var4, var2.size(), var1, var5);

         try {
            var6.kS();
         } catch (Exception var18) {
            if (!var6.pF && !var6.OB) {
               throw var18;
            }

            this.logError(true, "Error while processing classes.dex: %s", var18);
         }

         var2.add(var6);
         if (var6.Cu == 0) {
            break;
         }

         var4 += var6.sY;
      }

      var1++;
      if (this.DQ != null) {
         for (IInput var7 : this.DQ) {
            try (InputStream var8 = var7.getStream()) {
               var3 = IO.readInputStream(var8);
            } catch (IOException var17) {
               hK.catching(var17);
               return false;
            }

            var4 = 0;

            for (int var22 = 0; var4 < var3.length; var22++) {
               KD var28 = new KD(this, var3, var4, var2.size(), var1, var22);

               try {
                  var28.kS();
               } catch (Exception var14) {
                  this.logError(true, S.L("Error while processing classes%d.dex: %s"), var1 + 1, var14);
               }

               var2.add(var28);
               if (var28.Cu == 0) {
                  break;
               }

               var4 += var28.sY;
            }

            var1++;
         }
      }

      int var24 = ((KD)var2.get(0)).getVersion();

      for (KD var29 : var2.subList(1, var2.size())) {
         if (var29.getVersion() != var24) {
            this.addNotification(new UnitNotification(NotificationType.INFO, S.L("The Dex files of this multi-dex APK do not have the same version id")));
         }
      }

      if (var24 == 38 && this.ED < 100) {
         this.pC(100);
      } else if (var24 == 39 && this.ED < 110) {
         this.pC(110);
      } else if (var24 > 39 && this.ED < 1000) {
         this.pC(1000);
      }

      this.rl = new StatusLogDrip(50L, hK);
      this.Ek = 0;

      for (KD var30 : var2) {
         this.Ek = this.Ek + var30.wS();
      }

      this.z = 0;
      this.fI = new HashMap();
      this.WR = new bO(this);
      beo var27 = this.Sc();

      for (KD var9 : var2) {
         try {
            var9.pC(var27);
         } catch (Exception var13) {
            this.logError(true, S.L("Error while processing the dex class pools: %s"), var13);
         }
      }

      this.ld().sY();
      this.WR.pC();
      this.pF = new ArrayList(var2);
      this.xC = this.xC + var27.A();
      StringBuilder var32 = new StringBuilder();
      int var33 = 0;

      for (KD var11 : var2) {
         if (var11.Pe != null && !var11.Pe.isEmpty()) {
            this.UO.addAll(var11.Pe);
         }

         if (var11.Bf != null && !var11.Bf.isEmpty()) {
            this.eP.addAll(var11.Bf);
            Strings.ff(var32, "dex%d:bad_bc=%d,", var33, var11.Bf.size());
         }

         var33++;
      }

      if (var32.length() > 0) {
         JebCoreService.notifySilentExceptionToClient(new JebException("Dex unprocessed bytecode: " + var32));
      }

      if (this.sY.ld() > 0) {
         JebCoreService.notifySilentExceptionToClient(new JebException("Dex bad types: " + this.sY.ld()));
      }

      if (var24 >= 39 && var27.A > 0) {
         JebCoreService.notifySilentExceptionToClient(new JebException("Unsupported const-method-xxx opcodes"));
      }

      return true;
   }

   beo Sc() {
      beo.Sv var1 = new beo.Sv(this.sY().pC(), this.ld().pC(), this.oT().pC(), this.fI().pC(), this.gp().pC(), this.NS().pC(), this.vP().pC());
      beo var2 = new beo(this.fI, this.WR, var1, this.ED, this.wS, this.UT);
      var2.pC(this);
      var2.pC(new GK(this));
      return var2;
   }

   @Override
   public synchronized void addDex(IInput var1) throws IOException {
      byte[] var2;
      try (InputStream var3 = var1.getStream()) {
         var2 = IO.readInputStream(var3);
      }

      if (var2.length >= 2 && var2[0] == 80 && var2[1] == 75) {
         if (var1 instanceof FileInput) {
            File var28 = ((FileInput)var1).getFile();

            try (ZipBrowser var4 = new ZipBrowser(var28)) {
               var2 = var4.readEntry("classes.dex");
            }
         } else {
            File var29 = IO.createSafeTempFile();

            try {
               IO.writeFile(var29, var2);

               try (ZipBrowser var31 = new ZipBrowser(var29)) {
                  var2 = var31.readEntry("classes.dex");
               }
            } finally {
               var29.delete();
            }
         }
      }

      if (var2.length >= 4 && var2[0] == 100 && var2[1] == 101 && var2[2] == 120 && var2[3] == 10) {
         String var30 = Formatter.byteArrayToHex(Hash.calculateSHA256(var2)).toString();
         if (this.ZD == null) {
            this.ZD = new LinkedHashSet();
         }

         if (this.ZD.add(var30)) {
            int var32 = this.pF.size();
            int var5 = ((KD)this.pF.get(var32 - 1)).A + 1;
            ArrayList var6 = new ArrayList();
            int var7 = 0;

            for (int var8 = 0; var7 < var2.length; var8++) {
               KD var9 = new KD(this, var2, var7, this.pF.size(), var5, var8);

               try {
                  var9.kS();
               } catch (Exception var27) {
                  if (!var9.pF && !var9.OB) {
                     throw var27;
                  }

                  this.logError(true, "Error while processing classes.dex: %s", var27);
               }

               var6.add(var9);
               this.pF.add(var9);
               if (var9.Cu == 0) {
                  break;
               }

               var7 += var9.sY;
            }

            beo var33 = this.Sc();

            for (KD var11 : var6) {
               var11.pC(var33);
            }

            this.ld().sY();
            this.WR.pC();
            if (this.Kq != null) {
               this.Kq.pC();
               this.Kq = null;
            }

            if (this.JF != null) {
               this.JF.pC();
               this.JF = null;
            }

            this.xC = this.xC + var33.A();
            this.pC(true, new UnitChangeEventData(7, this, var6, null));
         }
      } else {
         throw new IOException("Entry does not look like a dex file");
      }
   }

   @Override
   public long getInstructionCount() {
      return this.xC;
   }

   public long pC(Object var1) {
      long var2 = 0L;
      if (var1 instanceof bfx) {
         return this.pC((bfx)var1);
      } else if (var1 instanceof qt) {
         return this.pC((qt)var1);
      } else if (var1 instanceof bfy) {
         return this.pC((bfy)var1);
      } else if (var1 instanceof bfs) {
         return this.pC((bfs)var1);
      } else if (var1 instanceof bft) {
         return this.pC((bft)var1);
      } else if (var1 instanceof bfu) {
         return this.pC((bfu)var1);
      } else if (var1 instanceof InstructionCoordinates) {
         return this.pC((InstructionCoordinates)var1);
      } else {
         if (var1 instanceof IdentifierCoordinates var4) {
            ICodeCoordinates var5 = var4.getBase();
            if (var5 instanceof MethodCoordinates var6) {
               if ((var6.getMethodId() & 0xFF000000) != 0 || (var4.getVariableIndex() & -65536) != 0) {
                  return 0L;
               }

               var2 = 576460752303423488L | (long)var6.getMethodId() << 32 | var4.getVariableIndex();
            } else if (var5 instanceof InstructionCoordinates var8) {
               if ((var8.getMethodId() & 0xFF000000) == 0 && (var8.getOffset() & -65536) == 0 && (var4.getVariableIndex() & -65536) == 0) {
                  var2 = 648518346341351424L | (long)var8.getMethodId() << 32 | (long)var8.getOffset() << 16 | var4.getVariableIndex();
               } else {
                  int var7 = var4.getVariableIndex() - 65536;
                  if ((var7 & -65536) != 0) {
                     return 0L;
                  }

                  var2 = 792633534417207296L | (long)var8.getMethodId() << 32 | (long)var8.getOffset() << 16 | var7;
               }
            }
         } else if (var1 instanceof Long) {
            return this.pC((Long)var1);
         }

         return var2;
      }
   }

   public long pC(bfx var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 72057594037927936L | var2;
   }

   public long pC(qt var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 144115188075855872L | var2;
   }

   public long pC(bfy var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 216172782113783808L | var2;
   }

   public long pC(bfs var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 288230376151711744L | var2;
   }

   public long pC(bft var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 360287970189639680L | var2;
   }

   public long pC(bfu var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 432345564227567616L | var2;
   }

   public long pC(InstructionCoordinates var1) {
      return (var1.getMethodId() & 0xFF000000) == 0 && var1.getOffset() >= 0 ? 504403158265495552L | (long)var1.getMethodId() << 32 | var1.getOffset() : 0L;
   }

   public long pC(Long var1) {
      Long var2 = this.pC(var1.longValue());
      if (var2 == null) {
         var2 = this.A(var1.longValue());
         if (var2 == null) {
            return 0L;
         }
      }

      if ((var2 & -4294967296L) != 0L) {
         Integer var3 = (Integer)this.PR.get(var2);
         if (var3 == null) {
            var3 = this.cX.size();
            this.cX.add(var2);
            this.PR.put(var2, var3);
         }

         var2 = 4294967296L | var3.intValue() & 4294967295L;
      }

      return 720575940379279360L | var2;
   }

   @Override
   public Object getObjectById(long var1) {
      if (var1 == 0L) {
         return null;
      } else {
         int var3 = (int)(var1 >> 56) & 127;
         int var4 = (int)(var1 >> 32) & 16777215;
         int var5 = (int)var1;
         Object var6 = null;
         if (var3 == 1) {
            var6 = this.sY().pC(var5);
         } else if (var3 == 2) {
            var6 = this.ld().A(var5);
         } else if (var3 == 3) {
            var6 = this.ld().pC(var5);
         } else if (var3 == 4) {
            if (var5 >= 0 && var5 < this.WR().pC()) {
               var6 = this.WR().pC(var5);
            }
         } else if (var3 == 5) {
            if (var5 >= 0 && var5 < this.oT().pC()) {
               var6 = this.oT().pC(var5);
            }
         } else if (var3 == 6) {
            if (var5 >= 0 && var5 < this.fI().pC()) {
               var6 = this.fI().pC(var5);
            }
         } else if (var3 == 7) {
            var6 = new InstructionCoordinates(var4, var5);
         } else {
            if (var3 == 8) {
               int var13 = (int)(var1 & 65535L);
               MethodCoordinates var9 = new MethodCoordinates(var4);
               return new IdentifierCoordinates(var9, var13);
            }

            if (var3 == 9) {
               int var12 = (int)(var1 & 65535L);
               int var14 = (int)(var1 >> 16 & 65535L);
               InstructionCoordinates var15 = new InstructionCoordinates(var4, var14);
               return new IdentifierCoordinates(var15, var12);
            }

            if (var3 == 11) {
               int var11 = (int)(var1 & 65535L) + 65536;
               int var8 = (int)(var1 >> 16 & 65535L);
               InstructionCoordinates var10 = new InstructionCoordinates(var4, var8);
               return new IdentifierCoordinates(var10, var11);
            }

            if (var3 == 10) {
               Long var7 = null;
               if (var4 == 0) {
                  var7 = var5 & 4294967295L;
               } else if (var4 == 1) {
                  var7 = (Long)this.cX.get(var5);
               }

               var6 = var7;
            }
         }

         return var6;
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Sv WR(String var1) {
      return this.NS(var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.Sv NS(String var1) {
      ICodeCoordinates var2 = this.A().pC(var1);
      if (var2 == null) {
         return null;
      } else if (var2 instanceof ClassCoordinates) {
         return this.ys(((ClassCoordinates)var2).getClassId());
      } else if (var2 instanceof FieldCoordinates) {
         return this.E(((FieldCoordinates)var2).getFieldId());
      } else if (var2 instanceof MethodCoordinates) {
         return this.sY(((MethodCoordinates)var2).getMethodId());
      } else {
         return var2 instanceof PackageCoordinates ? this.oT(((PackageCoordinates)var2).getPackageId()) : null;
      }
   }

   @Override
   public Object getItemObject(long var1) {
      return this.getObjectById(var1);
   }

   @Override
   public List getGlobalActions() {
      return Collections.emptyList();
   }

   @Override
   public List getItemActions(long var1) {
      Object var3 = this.getObjectById(var1);
      return var3 == null ? null : this.A(var3);
   }

   @Override
   public List getAddressActions(String var1) {
      ArrayList var2 = new ArrayList();
      if (var1 != null && this.A().pC(var1) != null) {
         var2.add(10);
         var2.add(3);
      }

      return var2;
   }

   @Override
   public String getAddressOfItem(long var1) {
      Object var3 = this.getObjectById(var1);
      if (var3 == null) {
         return null;
      } else if (var3 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Sv) {
         return ((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var3).getAddress();
      } else {
         return var3 instanceof ICodeCoordinates ? this.A().pC((ICodeCoordinates)var3) : null;
      }
   }

   @Override
   public List getRelatedItems(long var1) {
      if (!(this.getItemObject(var1) instanceof bfu var4)) {
         return Collections.emptyList();
      } else {
         List var5 = new bhc(this).kS(var4, false);
         ArrayList var6 = new ArrayList(var5.size());

         for (int var8 : var5) {
            var6.add(this.sY(var8).getItemId());
         }

         return var6;
      }
   }

   @Override
   public boolean isValidAddress(String var1) {
      return this.A().pC(var1) != null;
   }

   @Override
   public String getCanonicalAddress(String var1) {
      return this.A().pC(var1, false);
   }

   @Override
   public long getItemAtAddress(String var1) {
      return 0L;
   }

   public List A(Object var1) {
      ArrayList var2 = new ArrayList();
      if (var1 instanceof bfx) {
         var2.add(4);
         var2.add(5);
      } else if (var1 instanceof qt || var1 instanceof bfy) {
         var2.add(2);
         var2.add(11);
         var2.add(16);
         var2.add(12);
      } else if (var1 instanceof bfs) {
         var2.add(2);
         var2.add(4);
         var2.add(11);
         var2.add(16);
         var2.add(12);
      } else if (var1 instanceof bft) {
         var2.add(2);
         var2.add(13);
         var2.add(4);
      } else if (var1 instanceof bfu) {
         var2.add(2);
         var2.add(13);
         var2.add(4);
      } else if (var1 instanceof InstructionCoordinates) {
         var2.add(2);
         var2.add(4);
      } else if (var1 instanceof IdentifierCoordinates) {
         var2.add(2);
         var2.add(4);
      } else if (var1 instanceof Long) {
         var2.add(5);
      }

      return var2;
   }

   @Override
   public boolean canExecuteAction(ActionContext var1) {
      int var2 = var1.getActionId();
      String var3 = var1.getAddress();
      Object var4 = this.getObjectById(var1.getItemId());
      if (var2 == 14) {
         return true;
      } else if (var2 != 3 && var2 != 10) {
         List var5 = this.A(var4);
         return var5 != null && var5.contains(var2);
      } else {
         return var3 != null;
      }
   }

   @Override
   public boolean prepareExecution(ActionContext var1, IActionData var2) {
      int var3 = var1.getActionId();
      long var4 = var1.getItemId();
      String var6 = var1.getAddress();
      Object var7 = null;
      if (var4 != 0L) {
         var7 = this.getObjectById(var4);
      }

      if (var6 != null && var7 == null) {
         var7 = this.NS(var6);
      }

      if (var3 == 2) {
         if (var7 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Sv && !((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var7).pC()) {
            return false;
         }

         ActionRenameData var8 = (ActionRenameData)var2;
         if (var7 instanceof qt) {
            var8.setOriginalName(((qt)var7).getName(false));
            var8.setCurrentName(((qt)var7).getName(true));
         } else if (var7 instanceof bfs) {
            var8.setOriginalName(((bfs)var7).getName(false));
            var8.setCurrentName(((bfs)var7).getName(true));
         } else if (var7 instanceof bft) {
            var8.setOriginalName(((bft)var7).getName(false));
            var8.setCurrentName(((bft)var7).getName(true));
         } else if (var7 instanceof bfu) {
            var8.setOriginalName(((bfu)var7).getName(false));
            var8.setCurrentName(((bfu)var7).getName(true));
         } else if (var7 instanceof InstructionCoordinates) {
            var8.setCurrentName(this.pC((ICodeCoordinates)((InstructionCoordinates)var7)));
         } else if (var7 instanceof IdentifierCoordinates) {
            var8.setCurrentName(this.pC((IdentifierCoordinates)var7));
         }
      } else if (var3 == 3) {
         ActionCommentData var24 = (ActionCommentData)var2;
         String var9 = this.getInlineComment(var6);
         var24.setComment(var9);
      } else if (var3 == 10) {
         ActionCreatePackageData var25 = (ActionCreatePackageData)var2;
         if (var7 instanceof qt) {
            qt var32 = ((qt)var7).wS();
            if (var32 != null) {
               var25.setCurrentPackageFqname(var32.pC(true));
            }
         }
      } else if (var3 == 11) {
         ActionMoveToPackageData var26 = (ActionMoveToPackageData)var2;
         if (var7 instanceof qt) {
            qt var33 = ((qt)var7).wS();
            if (var33 != null) {
               var26.setCurrentPackageFqname(var33.pC(true));
            }
         }
      } else if (var3 == 16) {
         ActionMoveToData var27 = (ActionMoveToData)var2;
         if (var7 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Sv) {
            String var34 = ((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var7).getAddress(true);
            var27.setCurrentItemFqname(var34);
            HE var10 = this.sY.pC((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var7);
            if (var10 != null && var10.A() != null) {
               String var11 = var10.A().kS().getAddress(true);
               var27.setDstContainerFqname(var11);
            }
         }
      } else if (var3 == 4) {
         ActionXrefsData var28 = (ActionXrefsData)var2;
         DexPoolType var35;
         int var52;
         if (var7 instanceof bfx) {
            var35 = DexPoolType.STRING;
            var52 = ((bfx)var7).getIndex();
         } else if (var7 instanceof bfs) {
            var35 = DexPoolType.TYPE;
            var52 = ((bfs)var7).getClassTypeIndex();
         } else if (var7 instanceof bfy) {
            var35 = DexPoolType.TYPE;
            var52 = ((bfy)var7).getIndex();
         } else if (var7 instanceof bft) {
            var35 = DexPoolType.FIELD;
            var52 = ((bft)var7).getIndex();
         } else {
            if (!(var7 instanceof bfu)) {
               return false;
            }

            var35 = DexPoolType.METHOD;
            var52 = ((bfu)var7).getIndex();
         }

         ArrayList var56 = new ArrayList();
         ArrayList var12 = new ArrayList();
         ArrayList var13 = new ArrayList();

         for (IDexAddress var15 : this.getCrossReferences(var35, var52, var28.getHintMaxResults())) {
            var56.add(var15.getInternalAddress());
            var13.add(var15.getUserAddress());
            Object var16 = "";
            DexReferenceType var17 = var15.getReferenceType();
            if (var17 != DexReferenceType.UNKNOWN) {
               var16 = var16 + var17;
            }

            ICodeCoordinates var18 = this.A().pC(var15.getInternalAddress());
            if (var18 instanceof InstructionCoordinates) {
               try {
                  bfu var19 = this.sY(((InstructionCoordinates)var18).getMethodId());
                  IDalvikInstruction var20 = (IDalvikInstruction)var19.A()
                     .pC()
                     .getControlFlowGraph()
                     .getInstruction((long)((InstructionCoordinates)var18).getOffset());
                  if (!var16.isEmpty()) {
                     var16 = var16 + ": ";
                  }

                  var16 = var16 + var20.format(this);
               } catch (Exception var23) {
               }
            }

            String var64 = var15.getInfo();
            if (!Strings.isBlank(var64)) {
               var16 = var16 + " / " + var64;
            }

            var12.add(var16);
         }

         var28.setAddresses(var56);
         var28.setDetails(var12);
         var28.setUserAddresses(var13);
      } else if (var3 == 12) {
         ActionTypeHierarchyData var29 = (ActionTypeHierarchyData)var2;
         int var36 = -1;
         if (var7 instanceof bfs) {
            var36 = ((bfs)var7).getClassTypeIndex();
         } else if (var7 instanceof bfy) {
            var36 = ((bfy)var7).getIndex();
         }

         if (var36 >= 0) {
            bhe var53 = this.eP().pC(var36);
            if (var53 == null) {
               return false;
            }

            HE var57 = new HE(this.wS(var53.A()));

            for (int var63 : var53.wS()) {
               var57.A(new HE(this.wS(var63)));
            }

            if (var7 instanceof bfy) {
               var29.setBaseNode(var57);
            } else {
               HE var61 = new HE(this.wS(var53.kS()));
               var57.pC(var61);
               var29.setBaseNode(var61);
            }
         }
      } else if (var3 == 13) {
         ActionOverridesData var30 = (ActionOverridesData)var2;
         if (var7 instanceof bfu var37) {
            bhc var54 = new bhc(this);

            try {
               List var58 = var54.A(var37, false);
               List var62 = var54.pC(var37, false);
               var30.setItems(
                  (List)var58.stream().map(var1x -> this.sY(var1x)).collect(Collectors.toList()),
                  (List)var62.stream().map(var1x -> this.sY(var1x)).collect(Collectors.toList())
               );
            } catch (RuntimeException var22) {
               JebCoreService.notifySilentExceptionToClient(var22);
            }
         } else {
            if (!(var7 instanceof bft var38)) {
               return false;
            }

            bhb var55 = new bhb(this);

            try {
               List var59 = var55.pC(var38, false);
               var30.setItems(null, (List)var59.stream().map(var1x -> this.sY(var1x)).collect(Collectors.toList()));
            } catch (RuntimeException var21) {
               JebCoreService.notifySilentExceptionToClient(var21);
            }
         }
      } else if (var3 != 5 && var3 == 14) {
         ActionAutoRenameAllData var31 = (ActionAutoRenameAllData)var2;
         String var39 = "";
         var39 = var39 + "Perform a blanket renaming action on all class/field/method items that are deemed illegal per the selected policy.\n";
         var39 = var39 + "New names will take the form of: prefix (CLS, FLD, or MTH) followed by their Dex object id. ";
         var39 = var39 + "Items that were already renamed will be left untouched.\n";
         var39 = var39 + "\n";
         var39 = var39 + "List of supported policies:\n";
         var39 = var39 + Strings.ff("- %d: names containing characters outside the printable ascii set [0x21, 0x7E] are illegal\n", 10);
         var39 = var39 + Strings.ff("- %d: names containing characters outside the ascii set [alphas, digits, dollar, underscore] are illegal\n", 20);
         var39 = var39 + Strings.ff("- %d: all names are are illegal (i.e., auto-rename all)\n", 100);
         var39 = var39 + "\n";
         var39 = var39 + "An optional signature prefix may be used to specify where auto-renaming should be performed.\n";
         var39 = var39
            + "E.g., 'Lcom/abc/' to rename everything under com.abc.* and sub-packages; 'La/b/X;' to rename everything in the a.b.X class or interface.\n";
         var39 = var39 + "\n";
         var31.setDescription(var39);
         var31.setDefaultTargets(7);
         var31.setDefaultPolicy(20);
         var31.setDefaultFilter(null);
         return true;
      }

      return true;
   }

   @Override
   public Collection getCrossReferences(DexPoolType var1, int var2, int var3) {
      List var4 = this.ys().pC(var1, var2);
      if (var4 == null) {
         return this.ys().getReferences(var1, var2, var3);
      } else {
         ArrayList var5 = new ArrayList();

         for (int var7 : var4) {
            bfu var8 = this.fI(var7);
            int var9 = var7 - var8.A().pC().getInstructionsOffset();
            String var10 = Strings.ff("%s+%Xh", var8.getSignature(true), var9);
            var5.add(new DH(var10));
            if (var3 > 0 && var5.size() >= var3) {
               break;
            }
         }

         return var5;
      }
   }

   @Override
   public Collection getCrossReferences(DexPoolType var1, int var2) {
      return this.getCrossReferences(var1, var2, 0);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2) {
      return this.executeAction(var1, var2, true);
   }

   @Override
   public boolean executeAction(ActionContext var1, IActionData var2, boolean var3) {
      int var4 = var1.getActionId();
      String var5 = var1.getAddress();
      Object var6 = null;
      long var7 = var1.getItemId();
      if (var7 != 0L) {
         var6 = this.getObjectById(var7);
      }

      if (var4 == 2) {
         ActionRenameData var9 = (ActionRenameData)var2;
         String var10 = var9.getNewName();
         if (var6 instanceof qt var11) {
            if (var11.pC(var10, true, var3)) {
               return true;
            }
         } else if (var6 instanceof bfs var34) {
            if (var34.setName(var10, var3)) {
               return true;
            }
         } else if (var6 instanceof bft var35) {
            if (var35.setName(var10, var3)) {
               return true;
            }
         } else if (var6 instanceof bfu var36) {
            if (var36.setName(var10, var3)) {
               return true;
            }
         } else if (var6 instanceof InstructionCoordinates var37) {
            if (this.pC(var37, var10, var3)) {
               return true;
            }
         } else if (var6 instanceof IdentifierCoordinates var38) {
            int var12 = this.pC(var38, var10, !var9.isBypassNameChecks(), var3);
            if (var12 >= 1) {
               return true;
            }

            if (var12 == -1) {
               var9.setExecutionErrorCode(1);
            }
         }
      } else if (var4 == 3) {
         ActionCommentData var20 = (ActionCommentData)var2;
         if (this.pC(var5, 0, var20.getNewComment(), var3)) {
            return true;
         }
      } else if (var4 == 10) {
         ActionCreatePackageData var21 = (ActionCreatePackageData)var2;
         HE var27 = this.ld().pC(var21.getFqname(), true);
         if (var27 != null) {
            this.pC(var3, new UnitChangeEventData(4, var27.kS(), var21.getFqname()));
            return true;
         }
      } else {
         if (var4 == 11) {
            ActionMoveToPackageData var26 = (ActionMoveToPackageData)var2;
            if (!(var6 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Sv var33)) {
               return false;
            }

            qt var41 = this.ld().A(var26.getDstPackageFqname());
            if (var41 != null && var41 != var33) {
               return this.ld().pC(var33, var41, 0, true, var3);
            }

            return false;
         }

         if (var4 == 16) {
            ActionMoveToData var25 = (ActionMoveToData)var2;
            if (!(var6 instanceof com.pnfsoftware.jeb.corei.parsers.dex.Sv var32)) {
               return false;
            }

            String var40 = var25.getDstContainerFqname();
            com.pnfsoftware.jeb.corei.parsers.dex.Sv var43 = this.NS(var40);
            if (var43 == null) {
               if (!DexUtil.isInternalPackageName(var40, true, null, 0)) {
                  return false;
               }

               String var44 = var40.substring(1, var40.length() - 1).replace('/', '.');
               HE var45 = this.ld().pC(var44, true);
               if (var45 == null || !(var45.kS() instanceof qt)) {
                  return false;
               }

               var43 = var45.kS();
            }

            return this.ld().pC(var32, var43, var25.getFlags(), true, var3);
         }

         if (var4 == 4 || var4 == 12 || var4 == 13) {
            return true;
         }

         if (var4 == 5) {
            if (var6 instanceof bfx var23) {
               int var29 = this.A(var23);
               byte var30;
               if (var29 == 0) {
                  var30 = 1;
               } else if (var29 == 1) {
                  var30 = 2;
               } else if (var29 == 2) {
                  var30 = 0;
               } else {
                  var30 = 0;
               }

               this.pC(var23, var30);
            } else {
               if (!(var6 instanceof Long var24)) {
                  return false;
               }

               int var31 = this.kS(var24);
               if (var31 == 16) {
                  var31 = 10;
               } else if (var31 == 10) {
                  var31 = 8;
               } else if (var31 == 8) {
                  var31 = -1;
               } else if (var31 == -1) {
                  var31 = 16;
               }

               this.pC(var24, var31);
            }

            this.pC(var3, new UnitChangeEventData(6, var6));
            return true;
         }

         if (var4 == 14) {
            ActionAutoRenameAllData var22 = (ActionAutoRenameAllData)var2;
            int var28 = var22.getTargets();
            int var39 = var22.getPolicy();
            String var42 = var22.getFilter();
            DexBulkItemRenamer var13 = new DexBulkItemRenamer(this, var28, var39, var42);
            boolean var14 = var13.process(true, false);
            int var15 = var13.getCountOfCandidates();
            int var16 = var13.getCountOfExamined();
            int var17 = var13.getCountOfRenames();
            int var18 = var13.getCountOfFailures();
            var22.setRenamedCount(var17);
            if (var17 > 0 || var18 > 0 || !var14) {
               String var19 = Strings.ff(S.L("%d/%d items were auto-renamed (candidates: %d)"), var17, var16, var15);
               if (var18 > 0) {
                  var19 = var19 + "; ";
                  var19 = var19 + Strings.ff(S.L("%d items could not be renamed"), var18);
               }

               this.logInfo(true, var19);
               this.pC(var3, new UnitChangeEventData(1, this));
            }

            return var14;
         }
      }

      return false;
   }

   private int LM() {
      if (this.Nq == null) {
         synchronized (this) {
            if (this.Nq == null) {
               this.Nq = this.getPropertyManager().getInteger("PreferredBaseForIntegers");
            }
         }
      }

      return this.Nq;
   }

   public int kS(long var1) {
      Integer var3 = (Integer)this.ZN.get(var1);
      if (var3 == null) {
         int var4 = this.LM();
         if (var4 == 10 || var4 == 16 || var4 == 8) {
            return var4;
         }

         var3 = (Integer)this.OB.get(var1);
         if (var3 == null) {
            if ((var1 & -16777216L) == 2130706432L) {
               var3 = 16;
            } else {
               var3 = DexUtil.determineBestBase(var1);
            }

            this.OB.put(var1, var3);
         }
      }

      return var3;
   }

   public void pC(long var1, int var3) {
      if (var3 != 16 && var3 != 10 && var3 != 8 && var3 != -1) {
         this.ZN.remove(var1);
      } else {
         this.ZN.put(var1, var3);
      }
   }

   public int A(bfx var1) {
      Integer var2 = (Integer)this.pg.get(var1.getValue());
      return var2 == null ? 0 : var2;
   }

   public void pC(bfx var1, int var2) {
      if (var2 == 0) {
         this.pg.remove(var1.getValue());
      } else {
         this.pg.put(var1.getValue(), var2);
      }
   }

   @Override
   public IMetadataManager getMetadataManager() {
      synchronized (this) {
         if (this.UW == null) {
            this.UW = new MetadataManager();
            this.UW.addGroup(new sy(this));
         }

         return this.UW;
      }
   }

   @Override
   public DexCommentManager getCommentManager() {
      return this.isDisposed() ? null : this.xM;
   }

   public Collection pC(String var1, int var2, int var3) {
      ICodeCoordinates var4 = this.A().pC(var1);
      return (Collection)(var4 == null ? Collections.emptyList() : this.xM.getMetaCommentObjects2(var4, var2, var3));
   }

   public boolean pC(String var1, int var2, String var3, boolean var4) {
      return this.xM.setPrimary(var1, var3, var2, var4);
   }

   public String pC(String var1, int var2) {
      return this.xM.getPrimary(var1, var2);
   }

   @Override
   public boolean setInlineComment(String var1, String var2) {
      return this.pC(var1, 0, var2, true);
   }

   @Override
   public String getInlineComment(String var1) {
      return this.pC(var1, 0);
   }

   @Override
   public Map getInlineComments() {
      return this.vP(null);
   }

   public Map vP(String var1) {
      return this.A(0, var1);
   }

   public Map A(int var1, String var2) {
      HashMap var3 = new HashMap();

      for (Entry var5 : this.xM.getComments2().entrySet()) {
         ICodeCoordinates var6 = (ICodeCoordinates)var5.getKey();
         Comment var7 = (Comment)var5.getValue();
         if (var6 != null && var7 != null) {
            String var8 = this.A().pC(var6, false, true);
            if (var8 != null && (var2 == null || var8.startsWith(var2))) {
               String var9 = null;
               if (var1 == 0) {
                  var9 = var7.getInline();
               } else if (var1 == -1) {
                  var9 = var7.getPre();
               }

               if (var9 != null) {
                  String var10 = this.A().pC(var6);
                  var3.put(var10, var9);
               }
            }
         }
      }

      return var3;
   }

   public boolean pC(String var1, String var2, int var3, boolean var4) {
      ICodeCoordinates var5 = this.A().pC(var1);
      return var5 == null ? false : this.xM.addMetaComment2(var5, new MetaComment(var2, var3), var4);
   }

   public boolean pC(String var1, String var2, boolean var3) {
      ICodeCoordinates var4 = this.A().pC(var1);
      return var4 == null ? false : this.xM.removeMetaComment2(var4, new MetaComment(var2, 0), var3);
   }

   public String pC(ICodeCoordinates var1) {
      return (String)this.Aj.get(var1);
   }

   public boolean pC(ICodeCoordinates var1, String var2, boolean var3) {
      String var4 = (String)this.Aj.get(var1);
      if ((var4 != null || var2 != null) && (var4 == null || !var4.equals(var2))) {
         if (var2 != null && var2.length() == 0) {
            var2 = null;
         }

         this.Aj.put(var1, var2);
         this.pC(var3, new UnitChangeEventData(2, this, var2, var4, var1));
         return true;
      } else {
         return true;
      }
   }

   @Override
   public Map getAddressLabels() {
      HashMap var1 = new HashMap();

      for (ICodeCoordinates var3 : this.Aj.keySet()) {
         String var4 = this.A().pC(var3);
         var1.put(var4, (String)this.Aj.get(var3));
      }

      return var1;
   }

   public Map xC(String var1) {
      HashMap var2 = new HashMap();

      for (ICodeCoordinates var4 : this.Aj.keySet()) {
         String var5 = this.A().pC(var4, false, true);
         if (var5 != null && var5.startsWith(var1)) {
            String var6 = this.A().pC(var4);
            var2.put(var6, (String)this.Aj.get(var4));
         }
      }

      return var2;
   }

   @Override
   public String getAddressLabel(String var1) {
      ICodeCoordinates var2 = this.A().pC(var1);
      return var2 == null ? null : this.pC(var2);
   }

   public boolean A(String var1, String var2, boolean var3) {
      ICodeCoordinates var4 = this.A().pC(var1);
      return var4 == null ? false : this.pC(var4, var2, var3);
   }

   @Override
   public Collection getWellKnownAddresses(int var1, Predicate var2) {
      return new vi.Sv(var1, var2).pC();
   }

   @Override
   public Map getRenamedIdentifiers() {
      return Collections.unmodifiableMap(this.EX);
   }

   public Map ah() {
      return this.EX;
   }

   public String pC(IdentifierCoordinates var1) {
      return (String)this.EX.get(var1);
   }

   public int pC(IdentifierCoordinates var1, String var2, boolean var3, boolean var4) {
      if (var1 == null) {
         return 0;
      } else {
         String var5 = (String)this.EX.get(var1);
         if ((var5 != null || var2 != null) && (var5 == null || !var5.equals(var2))) {
            if (var2 != null && var2.length() == 0) {
               var2 = null;
            }

            bfu var6 = this.sY(var1.getMethodIndex());
            if (var6 != null && var6.getData() != null) {
               int var7 = var1.getMethodIndex();
               if (var2 == null) {
                  this.EX.remove(var1);
               } else {
                  Assert.a(var2.length() > 0);
                  if (this.gj.getSafe(var2).contains(var7) && var3) {
                     return -1;
                  }

                  this.EX.put(var1, var2);
                  this.gj.put(var2, var7);
               }

               this.gj.removeValue(var5, var7, true);
               this.pC(var4, new UnitChangeEventData(1, this, var2, var5, var1));
               return 1;
            } else {
               return 0;
            }
         } else {
            return 2;
         }
      }
   }

   public void pC(boolean var1, UnitChangeEventData var2) {
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange, var2));
      }
   }

   public void pC(boolean var1) {
      this.pC(var1, null);
   }

   public bgy A(boolean var1) {
      if (this.os == null || var1) {
         this.os = new bgy(this);
      }

      return this.os;
   }

   public bgy eP() {
      return this.A(false);
   }

   @Override
   public ICodeNode getTypeHierarchy(String var1, int var2, boolean var3) {
      bfy var4 = this.A(var1);
      if (var4 == null) {
         return null;
      } else {
         if (var2 < 0) {
            var2 = Integer.MAX_VALUE;
         } else if (var2 == 0) {
            return null;
         }

         bhd var5 = this.hK();
         bhe var6 = var5.pC(var4.getIndex());
         if (var6 == null) {
            return null;
         } else {
            HE var7 = new HE(this.wS(var6.A()));
            if (--var2 <= 0) {
               return var7;
            } else {
               ArrayDeque var8 = new ArrayDeque();
               var8.add(var7);

               while (!var8.isEmpty()) {
                  HE var9 = (HE)var8.remove();
                  var4 = (bfy)var9.kS();
                  var6 = var5.pC(var4.getIndex());
                  if (var6 != null) {
                     for (int var11 : var6.wS()) {
                        HE var12 = new HE(this.wS(var11));
                        var8.add(var12);
                        var9.A(var12);
                        if (--var2 <= 0) {
                           return var7;
                        }
                     }
                  }
               }

               if (var3) {
                  HE var18 = var7;

                  while (true) {
                     var4 = (bfy)var18.kS();
                     var6 = var5.pC(var4.getIndex());
                     int var19 = var6.kS();
                     if (var19 < 0) {
                        break;
                     }

                     HE var20 = new HE(this.wS(var19));
                     var20.A(var18);
                     var18 = var20;
                     if (--var2 <= 0) {
                        return var7;
                     }
                  }
               }

               return var7;
            }
         }
      }
   }

   @Override
   public String getAddressFromCodeCoordinates(ICodeCoordinates var1) {
      return this.A().pC(var1);
   }

   @Override
   public ICodeCoordinates getCodeCoordinatesFromAddress(String var1) {
      return this.A().pC(var1);
   }

   public synchronized boolean A(qt var1) {
      if (this.Cu == null) {
         String[] var2 = this.getPropertyManager().getString("WellKnownLibraryPackages").split(",");
         this.Cu = new HashSet(var2.length);
         this.hZ = false;

         for (String var6 : var2) {
            var6 = var6.trim();
            if (!var6.isEmpty()) {
               if (var6.equals("*")) {
                  this.hZ = true;
               } else {
                  this.Cu.add(var6);
               }
            }
         }
      }

      String var7 = var1.getName(false);
      if (var7 != null && !var7.isEmpty()) {
         var7 = var1.pC(false);
         if (var7 == null || var7.isEmpty()) {
            return false;
         } else if (this.hZ) {
            return true;
         } else {
            for (String var10 : this.Cu) {
               if (!var10.endsWith("*")) {
                  if (var7.equals(var10) || var7.startsWith(var10 + ".")) {
                     return true;
                  }
               } else {
                  String var11 = var10.substring(0, var10.length() - 1);
                  if (var7.startsWith(var11)) {
                     return true;
                  }
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      Strings.ff(var1, S.L("- Dalvik parsing mode: %s\n"), beo.A(this.ED));
      if (this.DQ != null && !this.DQ.isEmpty()) {
         Strings.ff(var1, S.L("- The initial input was multi-dex (%d)\n"), 1 + this.DQ.size());
      } else {
         Strings.ff(var1, S.L("- The initial input was single-dex\n"));
      }

      int var2 = this.getCountOfDexEntries();
      int var3 = 1 + this.getDexEntry(var2 - 1).getFileIndex();
      Strings.ff(var1, S.L("- Dex files: %d\n"), var3);
      Strings.ff(var1, S.L("- Dex entries: %d\n"), var2);
      if (this.E != null && this.sY != null && this.ld != null && this.gp != null && this.ys != null && this.oT != null) {
         Strings.ff(var1, "\n");
         Strings.ff(
            var1,
            S.L("Dex pools:\n- Strings     : %d\n- Types       : %d\n- Prototypes  : %d\n- Field refs  : %d\n- Method refs : %d\n- Classes     : %d\n"),
            this.E.pC(),
            this.sY.pC(),
            this.ys.pC(),
            this.ld.pC(),
            this.gp.pC(),
            this.oT.pC()
         );
      }

      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (bfs var9 : this.oT) {
         if (var9.sY() != null) {
            for (bgd var11 : var9.sY().kS()) {
               if (var11.isNative()) {
                  var6++;
               } else if (var11.isAbstract()) {
                  var5++;
               } else {
                  var7++;
               }

               var4++;
            }
         }
      }

      Strings.ff(var1, "\n");
      Strings.ff(
         var1,
         S.L("%d methods defined in classes/interfaces:\n- Bytecode impl.        : %d\n- Abstract (non-native) : %d\n- Native                : %d\n"),
         var4,
         var7,
         var5,
         var6
      );
      return var1.toString();
   }

   public int UO() {
      int var1 = 0;

      for (bfs var3 : this.oT) {
         if (var3.sY() != null) {
            for (bgd var5 : var3.sY().kS()) {
               if (var5.isNative()) {
                  var1++;
               }
            }
         }
      }

      return var1;
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      synchronized (this) {
         if (this.Bf == null) {
            String var3 = this.getPropertyManager().getString("AndroidJavadocRoot");
            if (!Strings.isBlank(var3) && (this.Pe == null || !this.Pe.equals(var3))) {
               this.Pe = var3;

               try {
                  Net var4 = ((Nj)RuntimeProjectUtil.findProject(this).getEnginesContext()).kS();
                  this.Bf = new Mh(this, var3, var4);
                  var1.add(this.Bf);
               } catch (Exception var6) {
                  hK.error(
                     S.L(
                        "The javadoc contribution for a Dex unit cannot be created.\nPlease update your properties, this javadoc root location seems illegal: %s"
                     ),
                     var3
                  );
                  hK.catching(var6);
               }
            } else {
               hK.debug(S.L("This javadoc root location is illegal: %s"), var3);
            }
         }

         if (this.OI == null) {
            this.OI = new zp(this);
            var1.add(this.OI);
         }

         return var1;
      }
   }

   @Override
   public IQuickStateObject generateQuickState() {
      if (!this.isProcessed()) {
         return null;
      } else {
         try {
            return new oP(this);
         } catch (IOException var2) {
            hK.error(S.L("Cannot generate quick state for unit %s"), this);
            hK.catchingSilent(var2);
            return null;
         }
      }
   }

   public boolean kS(int var1, String var2) {
      return this.pC(var1, var2, null, 0);
   }

   public boolean pC(int var1, String var2, String var3, int var4) {
      return this.Sc == null ? false : this.Sc.pC(var1, var2, var3, var4);
   }

   public boolean Ab() {
      return this.getPropertyManager().getBoolean("JarLibraryEnabled");
   }

   public String rl() {
      return this.getPropertyManager().getString("JarLibraryClasspath");
   }

   public String z() {
      return this.getPropertyManager().getString("JarLibraryFolder");
   }

   public baj Ek() {
      baj var1 = new baj();
      if (this.Ab()) {
         if (!Strings.isBlank(this.rl())) {
            var1.pC(this.rl());
         } else {
            String var2 = this.z();
            if (Strings.isBlank(var2)) {
               var2 = IO.expandPath("~/.jeb-android-libraries");
            }

            File var3 = new File(var2);
            if (!var3.exists()) {
               var3.mkdirs();
            }

            this.pC(var3);

            for (File var5 : IO.listFiles(var3)) {
               if (var5.getName().endsWith(".jar")) {
                  var1.pC(var5);
               }
            }
         }
      }

      return var1;
   }

   private void pC(File var1) {
      String var2 = "android-classes.jar";
      File var3 = new File(var1, var2);
      if (!var3.exists() || var3.length() != AssetManager.getAssetSize(var2)) {
         try (
            InputStream var4 = AssetManager.getAsset(var2);
            FileOutputStream var5 = new FileOutputStream(var3, false);
         ) {
            IO.copy(var4, var5);
         } catch (IOException var12) {
            hK.error(S.L("Cannot write file: %s"), var3.getPath());
            throw new RuntimeException(var12);
         }
      }
   }

   public bhd hK() {
      if (this.kU == null) {
         synchronized (this) {
            if (this.kU == null) {
               bhd var2 = bhd.pC;

               try {
                  var2 = new bhd(this);
               } catch (Throwable var5) {
                  hK.catching(var5, S.L("Cannot build the typegraph"));
               }

               this.kU = var2;
            }
         }
      }

      return this.kU;
   }

   public bgx Er() {
      if (this.Kq == null) {
         synchronized (this) {
            if (this.Kq == null) {
               int var2 = this.getPropertyManager().getInteger("CallgraphAsyncBuildGracePeriod");
               int var3 = this.getPropertyManager().getInteger("CallgraphAsyncRecDetGracePeriod");
               bgx var4 = new bgx(this, var2, var3);
               this.Kq = var4;
            }
         }
      }

      return this.Kq;
   }

   public bgw FE() {
      if (this.go == null) {
         synchronized (this) {
            if (this.go == null) {
               String var2 = this.getPropertyManager().getString("ContextInfoDb");
               bgw var3 = new bgw(var2);
               this.go = var3;
            }
         }
      }

      return this.go;
   }

   public bgz Aj() {
      if (this.JF == null) {
         synchronized (this) {
            if (this.JF == null) {
               int var2 = this.getPropertyManager().getInteger("CIDBMethodCountThresholdNoRegen");
               int var3 = this.getPropertyManager().getInteger("CIDBAsyncGenGracePeriod");
               bgz var4 = new bgz(this, var2, var3);
               this.JF = var4;
            }
         }
      }

      return this.JF;
   }

   @Override
   public IDexDecompilerUnit getDecompiler() {
      return (IDexDecompilerUnit)DecompilerHelper.getDecompiler(this, true);
   }

   @Override
   public IInputLocation addressToLocation(String var1) {
      ICodeCoordinates var2 = this.A().pC(var1);
      if (var2 instanceof InstructionCoordinates) {
         int var3 = ((InstructionCoordinates)var2).getMethodId();
         int var4 = ((InstructionCoordinates)var2).getOffset();
         bfu var5 = this.sY(var3);
         if (var5 != null && var5.getData() != null && var5.getData().getCodeItem() != null) {
            IDexCodeItem var6 = var5.getData().getCodeItem();
            int var7 = var6.getInstructionsOffset() + var4;
            int var8 = 0;
            IDalvikInstruction var9 = var6.getInstructionAt(var4);
            if (var9 != null) {
               var8 = var9.getSize();
            }

            IInput var10 = null;
            int var11 = var6.getDexEntryIndex();
            if (var11 < 0) {
               return null;
            }

            int var12 = this.getDexEntry(var11).getFileIndex();
            if (var12 >= 1) {
               if (var12 > this.UT().size()) {
                  return null;
               }

               var10 = (IInput)this.UT().get(var12 - 1);
            }

            return new FileInputRegionInformation(var7, var8, var10);
         }
      }

      return null;
   }

   @Override
   public String locationToAddress(IInputLocation var1) {
      return null;
   }

   @Override
   public void dispose() {
      super.dispose();
      if (this.Kq != null) {
         this.Kq.pC();
      }

      if (this.JF != null) {
         this.JF.pC();
      }
   }

   @Ser
   public static class Av {
      @SerId(1)
      private List pC = new ArrayList();
      @SerId(2)
      private boolean A;

      public boolean pC() {
         return this.pC == null;
      }

      public boolean pC(int var1, String var2, String var3, int var4) {
         if (this.pC == null) {
            return false;
         } else if (this.pC.size() >= 10000) {
            this.pC = null;
            return false;
         } else {
            this.pC.add(new vi.Av.Av(var1, var2, var3, var4));
            return true;
         }
      }

      public List A() {
         return this.pC == null ? Collections.emptyList() : this.pC;
      }

      @Override
      public String toString() {
         return this.pC == null ? "<aborted-history>" : this.pC.toString();
      }

      @Ser
      public static class Av {
         @SerId(1)
         int pC;
         @SerId(2)
         String A;
         @SerId(3)
         String kS;
         @SerId(4)
         int wS;

         Av(int var1, String var2, String var3, int var4) {
            this.pC = var1;
            this.A = var2;
            this.kS = var3;
            this.wS = var4;
         }

         @Override
         public String toString() {
            return Strings.ff("%d:%s:%s:0x%X", this.pC, this.A, this.kS, this.wS);
         }
      }
   }

   private class Sv {
      private List A;
      private int kS;
      private Predicate wS;

      Sv(int var2, Predicate var3) {
         if (var2 < 0) {
            var2 = Integer.MAX_VALUE;
         }

         this.kS = var2;
         this.wS = var3;
      }

      public List pC() {
         if (this.A == null) {
            this.A = new ArrayList(vi.this.oT.pC() + vi.this.gp.pC() + vi.this.ld.pC());
            if (!this.pC(vi.this.oT)) {
               return this.A;
            }

            if (!this.pC(vi.this.gp)) {
               return this.A;
            }

            if (!this.pC(vi.this.ld)) {
               return this.A;
            }
         }

         return this.A;
      }

      private boolean pC(bgg var1) {
         for (com.pnfsoftware.jeb.corei.parsers.dex.Sv var3 : var1) {
            if (var3.isInternal()) {
               if (!this.pC(var3.getSignature())) {
                  return false;
               }

               if (var3.isRenamed() && !this.pC(var3.getSignature(false))) {
                  return false;
               }
            }
         }

         return true;
      }

      boolean pC(String var1) {
         if (!Strings.isBlank(var1) && (this.wS == null || this.wS.test(var1))) {
            this.A.add(var1);
            if (this.A.size() >= this.kS) {
               return false;
            }
         }

         return true;
      }
   }
}
