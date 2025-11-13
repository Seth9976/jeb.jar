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
import com.pnfsoftware.jeb.core.units.code.android.DexCommentManager;
import com.pnfsoftware.jeb.core.units.code.android.DexConstantLibrary;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.IDexFile;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.IOptimizedDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAddress;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCodeItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
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
import com.pnfsoftware.jebglobal.bec;
import com.pnfsoftware.jebglobal.bia;
import com.pnfsoftware.jebglobal.bii;
import com.pnfsoftware.jebglobal.bip;
import com.pnfsoftware.jebglobal.bjb;
import com.pnfsoftware.jebglobal.bjj;
import com.pnfsoftware.jebglobal.bjl;
import com.pnfsoftware.jebglobal.bjm;
import com.pnfsoftware.jebglobal.bjn;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bjq;
import com.pnfsoftware.jebglobal.bjr;
import com.pnfsoftware.jebglobal.bjs;
import com.pnfsoftware.jebglobal.bjt;
import com.pnfsoftware.jebglobal.bju;
import com.pnfsoftware.jebglobal.bjv;
import com.pnfsoftware.jebglobal.bjy;
import com.pnfsoftware.jebglobal.bjz;
import com.pnfsoftware.jebglobal.bka;
import com.pnfsoftware.jebglobal.bkb;
import com.pnfsoftware.jebglobal.bkc;
import com.pnfsoftware.jebglobal.bkd;
import com.pnfsoftware.jebglobal.bke;
import com.pnfsoftware.jebglobal.bko;
import com.pnfsoftware.jebglobal.bkp;
import com.pnfsoftware.jebglobal.bkq;
import com.pnfsoftware.jebglobal.bks;
import com.pnfsoftware.jebglobal.bkt;
import com.pnfsoftware.jebglobal.bku;
import com.pnfsoftware.jebglobal.bkv;
import com.pnfsoftware.jebglobal.bkx;
import com.pnfsoftware.jebglobal.bky;
import com.pnfsoftware.jebglobal.bkz;
import com.pnfsoftware.jebglobal.bla;
import com.pnfsoftware.jebglobal.blb;
import com.pnfsoftware.jebglobal.zJ;
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
public class bK extends AbstractBinaryUnit implements IDexUnit {
   private static final String jq = "Unit is not processed";
   private static final ILogger ui = GlobalLog.getLogger(bK.class);
   @SerId(1)
   private byte[] TX;
   @SerId(2)
   boolean q;
   @SerId(3)
   boolean RF;
   @SerId(4)
   boolean xK;
   @SerId(5)
   boolean Dw;
   @SerId(6)
   boolean Uv;
   @SerId(value = 10, deprecated = true)
   @Deprecated
   private Map Rr = new HashMap();
   @SerId(11)
   private Map EB = new HashMap();
   @SerId(12)
   private Map Xo = new HashMap();
   @SerId(13)
   bkd oW;
   @SerId(14)
   bke gO;
   @SerId(15)
   bkc nf;
   @SerId(16)
   bjv gP;
   @SerId(17)
   bka za;
   @SerId(18)
   bjl lm;
   @SerId(19)
   HashMap zz;
   @SerId(20)
   oM JY;
   @SerTransient
   private vn Bu;
   @SerTransient
   private blb IN;
   @SerId(31)
   private bip[] rL;
   @SerTransient
   private bku eJ;
   @SerTransient
   private Set YN;
   @SerTransient
   private boolean Rv;
   @SerId(32)
   private MetadataManager zx;
   @SerId(33)
   private Map ZT = new HashMap();
   @SerId(34)
   private List Ri = new ArrayList();
   @SerId(40)
   private List GY = new ArrayList();
   @SerId(41)
   private Map Wx = new HashMap();
   @SerTransient
   private Map AB = new HashMap();
   @SerId(value = 42, version = 1)
   bjj HF;
   @SerId(value = 43, version = 1)
   bjz LK;
   @SerId(value = 44, version = 1)
   private List CY;
   @SerId(value = 45, version = 2)
   long io;
   @SerTransient
   private Integer WI;
   @SerTransient
   private EE Tq;
   @SerTransient
   private IUnitContribution Yp;
   @SerTransient
   private String Gu;
   @SerId(value = 46, version = 3)
   int qa;
   @SerId(value = 50, version = 4)
   bK.CU Hk;
   @SerTransient
   private Boolean nY;
   @SerTransient
   private Boolean lF;
   @SerId(value = 51, deprecated = true)
   @Deprecated
   private tw nq;
   @SerId(52)
   private DexCommentManager NX = new DexCommentManager(this);
   @SerId(53)
   DexConstantLibrary Me = new DexConstantLibrary(this);
   @SerTransient
   private volatile bkz br;
   @SerTransient
   private volatile bkt tW;
   @SerTransient
   private volatile bks ZA;
   @SerTransient
   private volatile bkv Ov;
   @SerTransient
   private volatile Integer Lj;
   @SerTransient
   public List PV = new ArrayList();
   @SerTransient
   public List oQ = new ArrayList();
   @SerId(54)
   private Map nv = new HashMap();
   @SerId(55)
   private MultiMap LL = new MultiMap();
   @SerId(56)
   private Set PQ;
   @SerId(value = 57, deprecated = true)
   public Map xW;
   public static final int KT = 112;
   public static final int Gf = 305419896;
   public static final int Ef = 2018915346;
   public static final int cC = -1;
   public static final int sH = 30239;
   public static final int CE = 20703;
   public static final int wF = 204287;
   @SerTransient
   StatusLogDrip If;
   @SerTransient
   int Dz;
   @SerTransient
   int mI;

   @SerCustomInitPostGraph
   private void Bu() {
      if (this.Wx == null) {
         this.Wx = new HashMap();
      }

      if (this.AB == null) {
         this.AB = new HashMap();
      }

      if (this.nv == null) {
         this.nv = new HashMap();
      }

      if (this.nq == null && this.Rr != null) {
         this.nq = new tw(this);
         this.nq.RF.putAll(this.Rr);
         this.Rr = null;
      }

      if (this.NX == null && this.nq != null) {
         this.NX = new DexCommentManager(this);

         for (Entry var2 : this.nq.q().entrySet()) {
            this.NX.setPrimary2((ICodeCoordinates)var2.getKey(), (String)var2.getValue(), 0, false);
         }

         for (Entry var8 : this.nq.RF().entrySet()) {
            for (tw.eo var4 : (Collection)var8.getValue()) {
               MetaComment var5 = new MetaComment(var4.q(), var4.RF());
               this.NX.addMetaComment2((ICodeCoordinates)var8.getKey(), var5, false);
            }
         }

         this.nq = null;
      }

      if (this.JY != null && this.JY.q == null) {
         this.JY.q = this;
      }

      if (this.Me == null) {
         this.Me = new DexConstantLibrary(this);
      }

      if (this.LL == null) {
         this.LL = new MultiMap();

         for (Entry var9 : this.Xo.entrySet()) {
            if (var9.getValue() != null && var9.getKey() != null) {
               this.LL.put((String)var9.getValue(), ((IdentifierCoordinates)var9.getKey()).getMethodIndex());
            }
         }
      }
   }

   public bK(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super(null, var2, "dex", var1, var3, var4, var5);
   }

   @Override
   public void onPropertyChange(String var1) {
      if (var1 != null && var1.endsWith(".ContextInfoDb")) {
         this.ZA = null;
      }

      if (var1 != null && var1.endsWith(".PreferredBaseForIntegers")) {
         this.Lj = null;
      }
   }

   public void q(int var1) {
      this.getPropertyManager().setInteger("DalvikParserMode", var1);
      this.qa = var1;
   }

   public int q() {
      return this.qa;
   }

   public synchronized int RF() {
      if (this.WI == null) {
         if (this.CY != null && !this.CY.isEmpty()) {
            this.WI = ((qV)this.CY.get(0)).Dw;
         } else {
            this.WI = 0;
         }
      }

      return this.WI;
   }

   @Override
   public int getCountOfDexEntries() {
      return this.CY.size();
   }

   @Override
   public List getDexEntries() {
      return Collections.unmodifiableList(this.CY);
   }

   @Override
   public IDexFile getDexEntry(int var1) {
      return (IDexFile)this.CY.get(var1);
   }

   public blb xK() {
      if (this.IN == null) {
         this.IN = new blb(this);
      }

      return this.IN;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new tl(this, 1L, S.L("Disassembly"), true), false);
      }

      return var1;
   }

   @Override
   public IDexDisassemblyDocument getDisassemblyDocument() {
      return new bjb(this);
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

   public void q(boolean var1) {
      this.q = var1;
   }

   public boolean Dw() {
      return this.q;
   }

   public void RF(boolean var1) {
      this.RF = var1;
   }

   public boolean Uv() {
      return this.RF;
   }

   public void xK(boolean var1) {
      this.xK = var1;
   }

   public boolean oW() {
      return this.xK;
   }

   public void Dw(boolean var1) {
      this.Dw = var1;
   }

   public boolean gO() {
      return this.Dw;
   }

   public void Uv(boolean var1) {
      this.Uv = var1;
   }

   public boolean nf() {
      return this.Uv;
   }

   public boolean gP() {
      if (this.nY == null) {
         this.nY = this.getPropertyManager().getBoolean("ProvideFriendlyCodeNodeLabels");
      }

      return this.nY;
   }

   public boolean za() {
      if (this.lF == null) {
         this.lF = this.getPropertyManager().getBoolean("ProvideExtraInfoInCodeNodeLabels");
      }

      return this.lF;
   }

   public List lm() {
      return Collections.unmodifiableList(this.GY);
   }

   public synchronized byte[] zz() throws IOException {
      if (this.TX == null) {
         try (InputStream var1 = this.getInput().getStream()) {
            this.TX = IO.readInputStream(var1);
         }
      }

      return this.TX;
   }

   public bkd JY() {
      if (this.oW == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.oW;
      }
   }

   public String RF(int var1) {
      return ((bjs)this.JY().q(var1)).getValue();
   }

   public String q(int var1, String var2) {
      bjs var3 = (bjs)this.JY().q(var1);
      return var3 == null ? var2 : var3.getValue();
   }

   public String q(int var1, String var2, boolean var3) {
      bjs var4 = (bjs)this.JY().q(var1);
      String var5 = var4 == null ? var2 : var4.getValue();
      return var3 ? Formatter.escapeString(var5) : var5;
   }

   @Override
   public List getStrings() {
      return this.JY().Uv();
   }

   public bjs xK(int var1) {
      return (bjs)this.JY().q(var1);
   }

   public bjs q(String var1) {
      return (bjs)this.JY().xK(var1);
   }

   @Override
   public int findStringIndex(String var1) {
      bjs var2 = (bjs)this.JY().xK(var1);
      return var2 == null ? -1 : var2.getIndex();
   }

   @Override
   public int getStringCount() {
      return this.JY().q();
   }

   public Set HF() {
      return this.zz.keySet();
   }

   public Long q(long var1) {
      return (Long)this.zz.get(var1);
   }

   public Long RF(long var1) {
      synchronized (this) {
         this.zz.put(var1, var1);
      }

      return (Long)this.zz.get(var1);
   }

   @Override
   public DexConstantLibrary getConstantsLibrary() {
      return this.Me;
   }

   public oM LK() {
      return this.JY;
   }

   @Override
   public int getBadTypeCount() {
      return this.io().lm();
   }

   @Override
   public List getTypes() {
      return this.io().Uv();
   }

   public bke io() {
      if (this.gO == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.gO;
      }
   }

   public bjt Dw(int var1) {
      return (bjt)this.io().q(var1);
   }

   public bjt RF(String var1) {
      return this.io().gO(var1);
   }

   public String q(int var1, boolean var2) {
      return this.io().q(var1, var2, true);
   }

   public String q(int var1, boolean var2, boolean var3) {
      return this.io().q(var1, var2, var3);
   }

   public String q(int var1, boolean var2, boolean var3, boolean var4) {
      return this.io().q(var1, var2, var3, var4);
   }

   public bkc qa() {
      if (this.nf == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.nf;
      }
   }

   @Override
   public List getPrototypes() {
      return this.qa().Uv();
   }

   public bjr Uv(int var1) {
      return (bjr)this.qa().q(var1);
   }

   public int Hk() {
      return this.qa().q();
   }

   public bjv Me() {
      if (this.gP == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.gP;
      }
   }

   public bjo oW(int var1) {
      return (bjo)this.Me().q(var1);
   }

   @Override
   public List getFields() {
      return this.Me().Uv();
   }

   public bjo xK(String var1) {
      return this.Me().q(var1);
   }

   @Override
   public IDexValue getStaticFieldInitializer(int var1) {
      bjo var2 = this.oW(var1);
      if (var2 == null) {
         return null;
      } else if (!var2.isInternal()) {
         return null;
      } else {
         bju var3 = var2.RF();
         if (var3 != null && var3.isStatic()) {
            bjn var4 = this.oQ().q(var2.getClassTypeSignature(false));
            if (var4 != null && var4.gP() != null) {
               bia[] var5 = var4.nf();
               int var6 = 0;

               for (bju var8 : var4.gP().getStaticFields()) {
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

   public bka PV() {
      if (this.za == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.za;
      }
   }

   public bjp gO(int var1) {
      return (bjp)this.PV().q(var1);
   }

   @Override
   public List getMethods() {
      return this.PV().Uv();
   }

   public bjp Dw(String var1) {
      return this.PV().q(var1);
   }

   public synchronized bjs Uv(String var1) {
      return this.q(var1, true);
   }

   public synchronized bjs q(String var1, boolean var2) {
      bjs var3 = (bjs)this.JY().xK(var1);
      return var3 != null ? var3 : this.JY().q(var1, var2, false);
   }

   public synchronized bjt oW(String var1) {
      bjt var2 = (bjt)this.io().xK(var1);
      if (var2 != null) {
         return var2;
      } else {
         this.q(var1, false);
         var2 = this.io().Uv(var1);
         this.io().nf();
         return var2;
      }
   }

   public synchronized bjr gO(String var1) {
      bjr var2 = (bjr)this.qa().xK(var1);
      if (var2 != null) {
         return var2;
      } else {
         String[] var3 = new String[1];
         List var4 = bjr.q(var1, var3);
         if (var4 == null) {
            return null;
         } else {
            int var5 = this.q(var3[0], false).getIndex();
            int var6 = -1;
            int[] var7 = new int[var4.size() - 1];
            int var8 = 0;

            for (String var10 : var4) {
               bjt var11 = this.oW(var10);
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

            return this.qa().q(var5, var6, var7);
         }
      }
   }

   public synchronized bjp nf(String var1) {
      bjp var2 = (bjp)this.PV().xK(var1);
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
               return this.xK(var5, var6, var7);
            }
         }
      }
   }

   public synchronized bjp q(String var1, String var2, String var3) {
      String var4 = var1 + "->" + var2 + var3;
      bjp var5 = (bjp)this.PV().xK(var4);
      return var5 != null ? var5 : this.xK(var1, var2, var3);
   }

   private bjp xK(String var1, String var2, String var3) {
      bjt var4 = this.oW(var1);
      if (var4 == null) {
         return null;
      } else {
         int var5 = this.q(var2, false).getIndex();
         bjr var6 = this.gO(var3);
         return var6 == null ? null : this.PV().q(var4.getIndex(), var6.getIndex(), var5);
      }
   }

   public synchronized bjo gP(String var1) {
      bjo var2 = (bjo)this.Me().xK(var1);
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
               return this.Dw(var5, var6, var7);
            }
         }
      }
   }

   public synchronized bjo RF(String var1, String var2, String var3) {
      String var4 = var1 + "->" + var2 + ":" + var3;
      bjo var5 = (bjo)this.Me().xK(var4);
      return var5 != null ? var5 : this.Dw(var1, var2, var3);
   }

   private bjo Dw(String var1, String var2, String var3) {
      bjt var4 = this.oW(var1);
      if (var4 == null) {
         return null;
      } else {
         int var5 = this.q(var2, false).getIndex();
         bjt var6 = this.oW(var3);
         return var6 == null ? null : this.Me().q(var4.getIndex(), var6.getIndex(), var5);
      }
   }

   public bjl oQ() {
      if (this.lm == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.lm;
      }
   }

   public bjn nf(int var1) {
      return (bjn)this.oQ().q(var1);
   }

   @Override
   public List getClasses() {
      return this.oQ().Uv();
   }

   public bjn za(String var1) {
      return this.oQ().q(var1);
   }

   bjj xW() {
      if (this.HF == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.HF;
      }
   }

   public bjm gP(int var1) {
      return (bjm)this.xW().q(var1);
   }

   @Override
   public List getCallSites() {
      return this.xW().Uv();
   }

   bjz KT() {
      if (this.LK == null && !this.isProcessed()) {
         throw new IllegalStateException("Unit is not processed");
      } else {
         return this.LK;
      }
   }

   public bjq za(int var1) {
      return (bjq)this.KT().q(var1);
   }

   @Override
   public List getMethodHandles() {
      return this.KT().Uv();
   }

   @Override
   public List getPackages() {
      return this.io().oW();
   }

   public Vj lm(String var1) {
      return this.io().Dw(var1);
   }

   public Vj lm(int var1) {
      return this.io().RF(var1);
   }

   public Vj zz(String var1) {
      oL var2 = this.io().q(var1);
      return var2 == null ? null : (Vj)var2.xK();
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

      return this.io().q((com.pnfsoftware.jeb.corei.parsers.dex.CU)var1, (com.pnfsoftware.jeb.corei.parsers.dex.CU)var2, var5, true, true);
   }

   public vn Gf() {
      if (this.Bu == null) {
         this.Bu = new vn(this);
      }

      return this.Bu;
   }

   public Object JY(String var1) {
      bjn var2 = this.oQ().q(var1);
      return var2 == null ? var1 : var2;
   }

   public bjp zz(int var1) {
      int var2 = 0;
      int var3 = this.rL.length;
      int var4 = 0;

      while (var3 > var2) {
         int var5 = var2 + (var3 - var2) / 2;
         bip var6 = this.rL[var5];
         int var7 = var6.getInstructionsOffset();
         if (var1 < var7) {
            var3 = var5;
         } else {
            if (var1 <= var7) {
               return (bjp)this.PV().q(var6.q());
            }

            var2 = var5 + 1;
            var4 = var5;
         }
      }

      if (var4 < this.rL.length) {
         bip var8 = this.rL[var4];
         if (var1 < var8.getInstructionsOffset() + var8.getInstructionsSize()) {
            return (bjp)this.PV().q(var8.q());
         }
      }

      return null;
   }

   public void q(IInput var1) {
      this.GY.add(var1);
   }

   public boolean Ef() {
      return !this.GY.isEmpty();
   }

   @Override
   protected boolean processInternal() {
      this.q = this.getPropertyManager().getBoolean("VerifyVersion");
      this.RF = this.getPropertyManager().getBoolean("VerifyHashes");
      this.xK = this.getPropertyManager().getBoolean("VerifyAccessFlags");
      this.Dw = this.getPropertyManager().getBoolean("ParseExtendedOpcodes");
      this.Uv = this.getPropertyManager().getBoolean("ParseOptimizedOpcodes");
      this.qa = this.getPropertyManager().getInteger("DalvikParserMode");
      if (this.qa >= 50 && this.getParent() instanceof IOptimizedDexUnit) {
         this.q(0);
      } else if (this.qa < 50 && this.getParent() instanceof IUnit && Strings.equalsIgnoreCase("oat", ((IUnit)this.getParent()).getFormatType())) {
         this.q(50);
      }

      this.Hk = new bK.CU();
      int var1 = 0;
      ArrayList var2 = new ArrayList();

      byte[] var3;
      try {
         var3 = this.zz();
      } catch (IOException var15) {
         return false;
      }

      int var4 = 0;

      for (int var5 = 0; var4 < var3.length; var5++) {
         qV var6 = new qV(this, var3, var4, var2.size(), var1, var5);

         try {
            var6.xK();
         } catch (Exception var18) {
            if (!var6.YN && !var6.eJ) {
               throw var18;
            }

            this.logError(true, "Error while processing classes.dex: %s", var18);
         }

         var2.add(var6);
         if (var6.TX == 0) {
            break;
         }

         var4 += var6.gO;
      }

      var1++;
      if (this.GY != null) {
         for (IInput var7 : this.GY) {
            try (InputStream var8 = var7.getStream()) {
               var3 = IO.readInputStream(var8);
            } catch (IOException var17) {
               ui.catching(var17);
               return false;
            }

            var4 = 0;

            for (int var22 = 0; var4 < var3.length; var22++) {
               qV var28 = new qV(this, var3, var4, var2.size(), var1, var22);

               try {
                  var28.xK();
               } catch (Exception var14) {
                  this.logError(true, S.L("Error while processing classes%d.dex: %s"), var1 + 1, var14);
               }

               var2.add(var28);
               if (var28.TX == 0) {
                  break;
               }

               var4 += var28.gO;
            }

            var1++;
         }
      }

      int var24 = ((qV)var2.get(0)).getVersion();

      for (qV var29 : var2.subList(1, var2.size())) {
         if (var29.getVersion() != var24) {
            this.addNotification(new UnitNotification(NotificationType.INFO, S.L("The Dex files of this multi-dex APK do not have the same version id")));
         }
      }

      if (var24 == 38 && this.qa < 100) {
         this.q(100);
      } else if (var24 == 39 && this.qa < 110) {
         this.q(110);
      } else if (var24 > 39 && this.qa < 1000) {
         this.q(1000);
      }

      this.If = new StatusLogDrip(50L, ui);
      this.mI = 0;

      for (qV var30 : var2) {
         this.mI = this.mI + var30.Dw();
      }

      this.Dz = 0;
      this.zz = new HashMap();
      this.JY = new oM(this);
      bii var27 = this.cC();

      for (qV var9 : var2) {
         try {
            var9.q(var27);
         } catch (Exception var13) {
            this.logError(true, S.L("Error while processing the dex class pools: %s"), var13);
         }
      }

      this.io().nf();
      this.JY.q();
      this.CY = new ArrayList(var2);
      this.io = this.io + var27.RF();
      StringBuilder var32 = new StringBuilder();
      int var33 = 0;

      for (qV var11 : var2) {
         if (var11.Ri != null && !var11.Ri.isEmpty()) {
            this.oQ.addAll(var11.Ri);
         }

         if (var11.ZT != null && !var11.ZT.isEmpty()) {
            this.PV.addAll(var11.ZT);
            Strings.ff(var32, "dex%d:bad_bc=%d,", var33, var11.ZT.size());
         }

         var33++;
      }

      if (var32.length() > 0) {
         JebCoreService.notifySilentExceptionToClient(new JebException("Dex unprocessed bytecode: " + var32));
      }

      if (this.gO.lm() > 0) {
         JebCoreService.notifySilentExceptionToClient(new JebException("Dex bad types: " + this.gO.lm()));
      }

      if (var24 >= 39 && var27.za > 0) {
         JebCoreService.notifySilentExceptionToClient(new JebException("Unsupported const-method-xxx opcodes"));
      }

      return true;
   }

   bii cC() {
      bii.CU var1 = new bii.CU(this.JY().q(), this.io().q(), this.Me().q(), this.PV().q(), this.qa().q(), this.xW().q(), this.KT().q());
      bii var2 = new bii(this.zz, this.JY, var1, this.qa, this.Dw, this.Uv);
      var2.q(this);
      var2.q(new Nz(this));
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
         if (this.PQ == null) {
            this.PQ = new LinkedHashSet();
         }

         if (this.PQ.add(var30)) {
            int var32 = this.CY.size();
            int var5 = ((qV)this.CY.get(var32 - 1)).RF + 1;
            ArrayList var6 = new ArrayList();
            int var7 = 0;

            for (int var8 = 0; var7 < var2.length; var8++) {
               qV var9 = new qV(this, var2, var7, this.CY.size(), var5, var8);

               try {
                  var9.xK();
               } catch (Exception var27) {
                  if (!var9.YN && !var9.eJ) {
                     throw var27;
                  }

                  this.logError(true, "Error while processing classes.dex: %s", var27);
               }

               var6.add(var9);
               this.CY.add(var9);
               if (var9.TX == 0) {
                  break;
               }

               var7 += var9.gO;
            }

            bii var33 = this.cC();

            for (qV var11 : var6) {
               var11.q(var33);
            }

            this.io().nf();
            this.JY.q();
            if (this.tW != null) {
               this.tW.q();
               this.tW = null;
            }

            if (this.Ov != null) {
               this.Ov.q();
               this.Ov = null;
            }

            this.io = this.io + var33.RF();
            this.q(true, new UnitChangeEventData(7, this, var6, null));
         }
      } else {
         throw new IOException("Entry does not look like a dex file");
      }
   }

   @Override
   public long getInstructionCount() {
      return this.io;
   }

   public long q(Object var1) {
      long var2 = 0L;
      if (var1 instanceof bjs) {
         return this.q((bjs)var1);
      } else if (var1 instanceof Vj) {
         return this.q((Vj)var1);
      } else if (var1 instanceof bjt) {
         return this.q((bjt)var1);
      } else if (var1 instanceof bjn) {
         return this.q((bjn)var1);
      } else if (var1 instanceof bjo) {
         return this.q((bjo)var1);
      } else if (var1 instanceof bjp) {
         return this.q((bjp)var1);
      } else if (var1 instanceof InstructionCoordinates) {
         return this.q((InstructionCoordinates)var1);
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
            return this.q((Long)var1);
         }

         return var2;
      }
   }

   public long q(bjs var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 72057594037927936L | var2;
   }

   public long q(Vj var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 144115188075855872L | var2;
   }

   public long q(bjt var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 216172782113783808L | var2;
   }

   public long q(bjn var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 288230376151711744L | var2;
   }

   public long q(bjo var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 360287970189639680L | var2;
   }

   public long q(bjp var1) {
      int var2 = var1.getIndex();
      return var2 < 0 ? 0L : 432345564227567616L | var2;
   }

   public long q(InstructionCoordinates var1) {
      return (var1.getMethodId() & 0xFF000000) == 0 && var1.getOffset() >= 0 ? 504403158265495552L | (long)var1.getMethodId() << 32 | var1.getOffset() : 0L;
   }

   public long q(Long var1) {
      Long var2 = this.q(var1.longValue());
      if (var2 == null) {
         var2 = this.RF(var1.longValue());
         if (var2 == null) {
            return 0L;
         }
      }

      if ((var2 & -4294967296L) != 0L) {
         Integer var3 = (Integer)this.ZT.get(var2);
         if (var3 == null) {
            var3 = this.Ri.size();
            this.Ri.add(var2);
            this.ZT.put(var2, var3);
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
            var6 = this.JY().q(var5);
         } else if (var3 == 2) {
            var6 = this.io().RF(var5);
         } else if (var3 == 3) {
            var6 = this.io().q(var5);
         } else if (var3 == 4) {
            if (var5 >= 0 && var5 < this.oQ().q()) {
               var6 = this.oQ().q(var5);
            }
         } else if (var3 == 5) {
            if (var5 >= 0 && var5 < this.Me().q()) {
               var6 = this.Me().q(var5);
            }
         } else if (var3 == 6) {
            if (var5 >= 0 && var5 < this.PV().q()) {
               var6 = this.PV().q(var5);
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
                  var7 = (Long)this.Ri.get(var5);
               }

               var6 = var7;
            }
         }

         return var6;
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.CU HF(String var1) {
      return this.LK(var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.dex.CU LK(String var1) {
      ICodeCoordinates var2 = this.xK().q(var1);
      if (var2 == null) {
         return null;
      } else if (var2 instanceof ClassCoordinates) {
         return this.nf(((ClassCoordinates)var2).getClassId());
      } else if (var2 instanceof FieldCoordinates) {
         return this.oW(((FieldCoordinates)var2).getFieldId());
      } else if (var2 instanceof MethodCoordinates) {
         return this.gO(((MethodCoordinates)var2).getMethodId());
      } else {
         return var2 instanceof PackageCoordinates ? this.lm(((PackageCoordinates)var2).getPackageId()) : null;
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
      return var3 == null ? null : this.RF(var3);
   }

   public String xK(long var1) {
      Object var3 = this.getObjectById(var1);
      return var3 == null ? null : var3.toString();
   }

   @Override
   public List getAddressActions(String var1) {
      ArrayList var2 = new ArrayList();
      if (var1 != null && this.xK().q(var1) != null) {
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
      } else if (var3 instanceof com.pnfsoftware.jeb.corei.parsers.dex.CU) {
         return ((com.pnfsoftware.jeb.corei.parsers.dex.CU)var3).getAddress();
      } else {
         return var3 instanceof ICodeCoordinates ? this.xK().q((ICodeCoordinates)var3) : null;
      }
   }

   @Override
   public List getRelatedItems(long var1) {
      if (!(this.getItemObject(var1) instanceof bjp var4)) {
         return Collections.emptyList();
      } else {
         List var5 = new bky(this).xK(var4, false);
         ArrayList var6 = new ArrayList(var5.size());

         for (int var8 : var5) {
            var6.add(this.gO(var8).getItemId());
         }

         return var6;
      }
   }

   @Override
   public boolean isValidAddress(String var1) {
      return this.xK().q(var1) != null;
   }

   @Override
   public String getCanonicalAddress(String var1) {
      return this.xK().q(var1, false);
   }

   @Override
   public long getItemAtAddress(String var1) {
      return 0L;
   }

   public List RF(Object var1) {
      ArrayList var2 = new ArrayList();
      if (var1 instanceof bjs) {
         var2.add(4);
         var2.add(5);
      } else if (var1 instanceof Vj || var1 instanceof bjt) {
         var2.add(2);
         var2.add(11);
         var2.add(16);
         var2.add(12);
      } else if (var1 instanceof bjn) {
         var2.add(2);
         var2.add(4);
         var2.add(11);
         var2.add(16);
         var2.add(12);
      } else if (var1 instanceof bjo) {
         var2.add(2);
         var2.add(13);
         var2.add(4);
      } else if (var1 instanceof bjp) {
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
         List var5 = this.RF(var4);
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
         var7 = this.LK(var6);
      }

      if (var3 == 2) {
         if (var7 instanceof com.pnfsoftware.jeb.corei.parsers.dex.CU && !((com.pnfsoftware.jeb.corei.parsers.dex.CU)var7).q()) {
            return false;
         }

         ActionRenameData var8 = (ActionRenameData)var2;
         if (var7 instanceof Vj) {
            var8.setOriginalName(((Vj)var7).getName(false));
            var8.setCurrentName(((Vj)var7).getName(true));
         } else if (var7 instanceof bjn) {
            var8.setOriginalName(((bjn)var7).getName(false));
            var8.setCurrentName(((bjn)var7).getName(true));
         } else if (var7 instanceof bjo) {
            var8.setOriginalName(((bjo)var7).getName(false));
            var8.setCurrentName(((bjo)var7).getName(true));
         } else if (var7 instanceof bjp) {
            var8.setOriginalName(((bjp)var7).getName(false));
            var8.setCurrentName(((bjp)var7).getName(true));
         } else if (var7 instanceof InstructionCoordinates) {
            var8.setCurrentName(this.RF((ICodeCoordinates)((InstructionCoordinates)var7)));
         } else if (var7 instanceof IdentifierCoordinates) {
            var8.setCurrentName(this.q((IdentifierCoordinates)var7));
         }
      } else if (var3 == 3) {
         ActionCommentData var24 = (ActionCommentData)var2;
         String var9 = this.getInlineComment(var6);
         var24.setComment(var9);
      } else if (var3 == 10) {
         ActionCreatePackageData var25 = (ActionCreatePackageData)var2;
         if (var7 instanceof Vj) {
            Vj var32 = ((Vj)var7).Dw();
            if (var32 != null) {
               var25.setCurrentPackageFqname(var32.q(true));
            }
         }
      } else if (var3 == 11) {
         ActionMoveToPackageData var26 = (ActionMoveToPackageData)var2;
         if (var7 instanceof Vj) {
            Vj var33 = ((Vj)var7).Dw();
            if (var33 != null) {
               var26.setCurrentPackageFqname(var33.q(true));
            }
         }
      } else if (var3 == 16) {
         ActionMoveToData var27 = (ActionMoveToData)var2;
         if (var7 instanceof com.pnfsoftware.jeb.corei.parsers.dex.CU) {
            String var34 = ((com.pnfsoftware.jeb.corei.parsers.dex.CU)var7).getAddress(true);
            var27.setCurrentItemFqname(var34);
            oL var10 = this.gO.q((com.pnfsoftware.jeb.corei.parsers.dex.CU)var7);
            if (var10 != null && var10.RF() != null) {
               String var11 = var10.RF().xK().getAddress(true);
               var27.setDstContainerFqname(var11);
            }
         }
      } else if (var3 == 4) {
         ActionXrefsData var28 = (ActionXrefsData)var2;
         DexPoolType var35;
         int var52;
         if (var7 instanceof bjs) {
            var35 = DexPoolType.STRING;
            var52 = ((bjs)var7).getIndex();
         } else if (var7 instanceof bjn) {
            var35 = DexPoolType.TYPE;
            var52 = ((bjn)var7).getClassTypeIndex();
         } else if (var7 instanceof bjt) {
            var35 = DexPoolType.TYPE;
            var52 = ((bjt)var7).getIndex();
         } else if (var7 instanceof bjo) {
            var35 = DexPoolType.FIELD;
            var52 = ((bjo)var7).getIndex();
         } else {
            if (!(var7 instanceof bjp)) {
               return false;
            }

            var35 = DexPoolType.METHOD;
            var52 = ((bjp)var7).getIndex();
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

            ICodeCoordinates var18 = this.xK().q(var15.getInternalAddress());
            if (var18 instanceof InstructionCoordinates) {
               try {
                  bjp var19 = this.gO(((InstructionCoordinates)var18).getMethodId());
                  IDalvikInstruction var20 = (IDalvikInstruction)var19.RF()
                     .q()
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
         if (var7 instanceof bjn) {
            var36 = ((bjn)var7).getClassTypeIndex();
         } else if (var7 instanceof bjt) {
            var36 = ((bjt)var7).getIndex();
         }

         if (var36 >= 0) {
            bla var53 = this.CE().q(var36);
            if (var53 == null) {
               return false;
            }

            oL var57 = new oL(this.Dw(var53.RF()));

            for (int var63 : var53.Uv()) {
               var57.RF(new oL(this.Dw(var63)));
            }

            if (var7 instanceof bjt) {
               var29.setBaseNode(var57);
            } else {
               oL var61 = new oL(this.Dw(var53.xK()));
               var57.q(var61);
               var29.setBaseNode(var61);
            }
         }
      } else if (var3 == 13) {
         ActionOverridesData var30 = (ActionOverridesData)var2;
         if (var7 instanceof bjp var37) {
            bky var54 = new bky(this);

            try {
               List var58 = var54.RF(var37, false);
               List var62 = var54.q(var37, false);
               var30.setItems(
                  (List)var58.stream().map(var1x -> this.gO(var1x)).collect(Collectors.toList()),
                  (List)var62.stream().map(var1x -> this.gO(var1x)).collect(Collectors.toList())
               );
            } catch (RuntimeException var22) {
               JebCoreService.notifySilentExceptionToClient(var22);
            }
         } else {
            if (!(var7 instanceof bjo var38)) {
               return false;
            }

            bkx var55 = new bkx(this);

            try {
               List var59 = var55.q(var38, false);
               var30.setItems(null, (List)var59.stream().map(var1x -> this.gO(var1x)).collect(Collectors.toList()));
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
         var31.setDefaultPolicy(20);
         return true;
      }

      return true;
   }

   @Override
   public Collection getCrossReferences(DexPoolType var1, int var2, int var3) {
      List var4 = this.LK().q(var1, var2);
      if (var4 == null) {
         return this.LK().getReferences(var1, var2, var3);
      } else {
         ArrayList var5 = new ArrayList();

         for (int var7 : var4) {
            bjp var8 = this.zz(var7);
            int var9 = var7 - var8.RF().q().getInstructionsOffset();
            String var10 = Strings.ff("%s+%Xh", var8.getSignature(true), var9);
            var5.add(new iZ(var10));
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
         if (var6 instanceof Vj var11) {
            if (var11.q(var10, true, var3)) {
               return true;
            }
         } else if (var6 instanceof bjn var30) {
            if (var30.q(var10, var3)) {
               return true;
            }
         } else if (var6 instanceof bjo var31) {
            if (var31.q(var10, var3)) {
               return true;
            }
         } else if (var6 instanceof bjp var32) {
            if (var32.q(var10, var3)) {
               return true;
            }
         } else if (var6 instanceof InstructionCoordinates var33) {
            if (this.q(var33, var10, var3)) {
               return true;
            }
         } else if (var6 instanceof IdentifierCoordinates var34) {
            int var12 = this.q(var34, var10, !var9.isBypassNameChecks(), var3);
            if (var12 >= 1) {
               return true;
            }

            if (var12 == -1) {
               var9.setExecutionErrorCode(1);
            }
         }
      } else if (var4 == 3) {
         ActionCommentData var16 = (ActionCommentData)var2;
         if (this.q(var5, 0, var16.getNewComment(), var3)) {
            return true;
         }
      } else if (var4 == 10) {
         ActionCreatePackageData var17 = (ActionCreatePackageData)var2;
         oL var23 = this.io().q(var17.getFqname(), true);
         if (var23 != null) {
            this.q(var3, new UnitChangeEventData(4, var23.xK(), var17.getFqname()));
            return true;
         }
      } else {
         if (var4 == 11) {
            ActionMoveToPackageData var22 = (ActionMoveToPackageData)var2;
            if (!(var6 instanceof com.pnfsoftware.jeb.corei.parsers.dex.CU var29)) {
               return false;
            }

            Vj var37 = this.io().RF(var22.getDstPackageFqname());
            if (var37 != null && var37 != var29) {
               return this.io().q(var29, var37, 0, true, var3);
            }

            return false;
         }

         if (var4 == 16) {
            ActionMoveToData var21 = (ActionMoveToData)var2;
            if (!(var6 instanceof com.pnfsoftware.jeb.corei.parsers.dex.CU var28)) {
               return false;
            }

            String var36 = var21.getDstContainerFqname();
            com.pnfsoftware.jeb.corei.parsers.dex.CU var39 = this.LK(var36);
            if (var39 == null) {
               if (!DexUtil.isInternalPackageName(var36, true, null, 0)) {
                  return false;
               }

               String var40 = var36.substring(1, var36.length() - 1).replace('/', '.');
               oL var41 = this.io().q(var40, true);
               if (var41 == null || !(var41.xK() instanceof Vj)) {
                  return false;
               }

               var39 = var41.xK();
            }

            return this.io().q(var28, var39, var21.getFlags(), true, var3);
         }

         if (var4 == 4 || var4 == 12 || var4 == 13) {
            return true;
         }

         if (var4 == 5) {
            if (var6 instanceof bjs var19) {
               int var25 = this.RF(var19);
               byte var26;
               if (var25 == 0) {
                  var26 = 1;
               } else if (var25 == 1) {
                  var26 = 2;
               } else if (var25 == 2) {
                  var26 = 0;
               } else {
                  var26 = 0;
               }

               this.q(var19, var26);
            } else {
               if (!(var6 instanceof Long var20)) {
                  return false;
               }

               int var27 = this.Uv(var20);
               if (var27 == 16) {
                  var27 = 10;
               } else if (var27 == 10) {
                  var27 = 8;
               } else if (var27 == 8) {
                  var27 = -1;
               } else if (var27 == -1) {
                  var27 = 16;
               }

               this.q(var20, var27);
            }

            this.q(var3, new UnitChangeEventData(6, var6));
            return true;
         }

         if (var4 == 14) {
            ActionAutoRenameAllData var18 = (ActionAutoRenameAllData)var2;
            int var24 = var18.getPolicy();
            String var35 = var18.getFilter();
            bK.eo var38 = new bK.eo(var24, var35);
            int var13 = var38.q();
            boolean var14 = var38.q(true);
            var18.setRenamedCount(var38.gP);
            if (var38.gP > 0 || var38.za > 0 || !var14) {
               String var15 = Strings.ff(S.L("%d/%d items were auto-renamed (candidates: %d)"), var38.gP, var38.nf, var13);
               if (var38.za > 0) {
                  var15 = var15 + "; ";
                  var15 = var15 + Strings.ff(S.L("%d items could not be renamed"), var38.za);
               }

               this.logInfo(true, var15);
               this.q(var3, new UnitChangeEventData(1, this));
            }

            return var14;
         }
      }

      return false;
   }

   public boolean Dw(long var1) {
      return this.Wx.containsKey(var1);
   }

   private int IN() {
      if (this.Lj == null) {
         synchronized (this) {
            if (this.Lj == null) {
               this.Lj = this.getPropertyManager().getInteger("PreferredBaseForIntegers");
            }
         }
      }

      return this.Lj;
   }

   public int Uv(long var1) {
      Integer var3 = (Integer)this.Wx.get(var1);
      if (var3 == null) {
         int var4 = this.IN();
         if (var4 == 10 || var4 == 16 || var4 == 8) {
            return var4;
         }

         var3 = (Integer)this.AB.get(var1);
         if (var3 == null) {
            if ((var1 & -16777216L) == 2130706432L) {
               var3 = 16;
            } else {
               var3 = DexUtil.determineBestBase(var1);
            }

            this.AB.put(var1, var3);
         }
      }

      return var3;
   }

   public void q(long var1, int var3) {
      if (var3 != 16 && var3 != 10 && var3 != 8 && var3 != -1) {
         this.Wx.remove(var1);
      } else {
         this.Wx.put(var1, var3);
      }
   }

   public int RF(bjs var1) {
      Integer var2 = (Integer)this.nv.get(var1.getValue());
      return var2 == null ? 0 : var2;
   }

   public void q(bjs var1, int var2) {
      if (var2 == 0) {
         this.nv.remove(var1.getValue());
      } else {
         this.nv.put(var1.getValue(), var2);
      }
   }

   @Override
   public IMetadataManager getMetadataManager() {
      synchronized (this) {
         if (this.zx == null) {
            this.zx = new MetadataManager();
            this.zx.addGroup(new vb(this));
         }

         return this.zx;
      }
   }

   @Override
   public DexCommentManager getCommentManager() {
      return this.isDisposed() ? null : this.NX;
   }

   public Comment q(ICodeCoordinates var1) {
      return this.NX.getComment2(var1);
   }

   public Comment io(String var1) {
      return this.NX.getComment(var1);
   }

   public Map qa(String var1) {
      HashMap var2 = new HashMap();

      for (Entry var4 : this.NX.getComments2().entrySet()) {
         ICodeCoordinates var5 = (ICodeCoordinates)var4.getKey();
         Comment var6 = (Comment)var4.getValue();
         if (var5 != null && var6 != null) {
            String var7 = this.xK().q(var5, false, true);
            if (var7 != null && var7.startsWith(var1)) {
               String var8 = this.xK().q(var5);
               var2.put(var8, var6);
            }
         }
      }

      return var2;
   }

   public boolean q(String var1, Comment var2) {
      return this.q(var1, var2, true);
   }

   public boolean q(String var1, Comment var2, boolean var3) {
      return this.NX.setComment(var1, var2, var3);
   }

   public Collection q(String var1, int var2, int var3) {
      ICodeCoordinates var4 = this.xK().q(var1);
      return (Collection)(var4 == null ? Collections.emptyList() : this.NX.getMetaCommentObjects2(var4, var2, var3));
   }

   public boolean q(String var1, int var2, String var3, boolean var4) {
      return this.NX.setPrimary(var1, var3, var2, var4);
   }

   public String q(String var1, int var2) {
      return this.NX.getPrimary(var1, var2);
   }

   @Override
   public boolean setInlineComment(String var1, String var2) {
      return this.q(var1, 0, var2, true);
   }

   @Override
   public String getInlineComment(String var1) {
      return this.q(var1, 0);
   }

   @Override
   public Map getInlineComments() {
      return this.Hk(null);
   }

   public Map Hk(String var1) {
      return this.RF(0, var1);
   }

   public Map Me(String var1) {
      return this.RF(-1, var1);
   }

   public Map RF(int var1, String var2) {
      HashMap var3 = new HashMap();

      for (Entry var5 : this.NX.getComments2().entrySet()) {
         ICodeCoordinates var6 = (ICodeCoordinates)var5.getKey();
         Comment var7 = (Comment)var5.getValue();
         if (var6 != null && var7 != null) {
            String var8 = this.xK().q(var6, false, true);
            if (var8 != null && (var2 == null || var8.startsWith(var2))) {
               String var9 = null;
               if (var1 == 0) {
                  var9 = var7.getInline();
               } else if (var1 == -1) {
                  var9 = var7.getPre();
               }

               if (var9 != null) {
                  String var10 = this.xK().q(var6);
                  var3.put(var10, var9);
               }
            }
         }
      }

      return var3;
   }

   public boolean q(String var1, String var2, int var3, boolean var4) {
      ICodeCoordinates var5 = this.xK().q(var1);
      return var5 == null ? false : this.NX.addMetaComment2(var5, new MetaComment(var2, var3), var4);
   }

   public boolean q(String var1, String var2, boolean var3) {
      ICodeCoordinates var4 = this.xK().q(var1);
      return var4 == null ? false : this.NX.removeMetaComment2(var4, new MetaComment(var2, 0), var3);
   }

   public String RF(ICodeCoordinates var1) {
      return (String)this.EB.get(var1);
   }

   public boolean q(ICodeCoordinates var1, String var2, boolean var3) {
      String var4 = (String)this.EB.get(var1);
      if ((var4 != null || var2 != null) && (var4 == null || !var4.equals(var2))) {
         if (var2 != null && var2.length() == 0) {
            var2 = null;
         }

         this.EB.put(var1, var2);
         this.q(var3, new UnitChangeEventData(2, this, var2, var4, var1));
         return true;
      } else {
         return true;
      }
   }

   @Override
   public Map getAddressLabels() {
      HashMap var1 = new HashMap();

      for (ICodeCoordinates var3 : this.EB.keySet()) {
         String var4 = this.xK().q(var3);
         var1.put(var4, (String)this.EB.get(var3));
      }

      return var1;
   }

   public Map PV(String var1) {
      HashMap var2 = new HashMap();

      for (ICodeCoordinates var4 : this.EB.keySet()) {
         String var5 = this.xK().q(var4, false, true);
         if (var5 != null && var5.startsWith(var1)) {
            String var6 = this.xK().q(var4);
            var2.put(var6, (String)this.EB.get(var4));
         }
      }

      return var2;
   }

   @Override
   public String getAddressLabel(String var1) {
      ICodeCoordinates var2 = this.xK().q(var1);
      return var2 == null ? null : this.RF(var2);
   }

   public boolean RF(String var1, String var2, boolean var3) {
      ICodeCoordinates var4 = this.xK().q(var1);
      return var4 == null ? false : this.q(var4, var2, var3);
   }

   @Override
   public Collection getWellKnownAddresses(int var1, Predicate var2) {
      return new bK.nI(var1, var2).q();
   }

   @Override
   public Map getRenamedIdentifiers() {
      return Collections.unmodifiableMap(this.Xo);
   }

   public Map sH() {
      return this.Xo;
   }

   public String q(IdentifierCoordinates var1) {
      return (String)this.Xo.get(var1);
   }

   public int q(IdentifierCoordinates var1, String var2, boolean var3, boolean var4) {
      if (var1 == null) {
         return 0;
      } else {
         String var5 = (String)this.Xo.get(var1);
         if ((var5 != null || var2 != null) && (var5 == null || !var5.equals(var2))) {
            if (var2 != null && var2.length() == 0) {
               var2 = null;
            }

            bjp var6 = this.gO(var1.getMethodIndex());
            if (var6 != null && var6.getData() != null) {
               int var7 = var1.getMethodIndex();
               if (var2 == null) {
                  this.Xo.remove(var1);
               } else {
                  Assert.a(var2.length() > 0);
                  if (this.LL.getSafe(var2).contains(var7) && var3) {
                     return -1;
                  }

                  this.Xo.put(var1, var2);
                  this.LL.put(var2, var7);
               }

               this.LL.removeValue(var5, var7, true);
               this.q(var4, new UnitChangeEventData(1, this, var2, var5, var1));
               return 1;
            } else {
               return 0;
            }
         } else {
            return 2;
         }
      }
   }

   public void q(boolean var1, UnitChangeEventData var2) {
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange, var2));
      }
   }

   public void q(UnitChangeEventData var1) {
      this.q(true, var1);
   }

   public void oW(boolean var1) {
      this.q(var1, null);
   }

   public bku gO(boolean var1) {
      if (this.eJ == null || var1) {
         this.eJ = new bku(this);
      }

      return this.eJ;
   }

   public bku CE() {
      return this.gO(false);
   }

   public List q(IDexMethod var1, int var2) {
      bky var4 = new bky(this);
      List var3;
      if (var2 == 0) {
         var3 = var4.RF(var1, false);
      } else if (var2 == 1) {
         var3 = var4.q(var1, false);
      } else if (var2 == 2) {
         var3 = var4.xK(var1, false);
      } else {
         if (var2 != 3) {
            throw new IllegalArgumentException();
         }

         var3 = var4.q(var1);
      }

      return (List)var3.stream().map(var1x -> this.gO(var1x)).collect(Collectors.toList());
   }

   @Override
   public ICodeNode getTypeHierarchy(String var1, int var2, boolean var3) {
      bjt var4 = this.RF(var1);
      if (var4 == null) {
         return null;
      } else {
         if (var2 < 0) {
            var2 = Integer.MAX_VALUE;
         } else if (var2 == 0) {
            return null;
         }

         bkz var5 = this.TX();
         bla var6 = var5.q(var4.getIndex());
         if (var6 == null) {
            return null;
         } else {
            oL var7 = new oL(this.Dw(var6.RF()));
            if (--var2 <= 0) {
               return var7;
            } else {
               ArrayDeque var8 = new ArrayDeque();
               var8.add(var7);

               while (!var8.isEmpty()) {
                  oL var9 = (oL)var8.remove();
                  var4 = (bjt)var9.xK();
                  var6 = var5.q(var4.getIndex());
                  if (var6 != null) {
                     for (int var11 : var6.Uv()) {
                        oL var12 = new oL(this.Dw(var11));
                        var8.add(var12);
                        var9.RF(var12);
                        if (--var2 <= 0) {
                           return var7;
                        }
                     }
                  }
               }

               if (var3) {
                  oL var18 = var7;

                  while (true) {
                     var4 = (bjt)var18.xK();
                     var6 = var5.q(var4.getIndex());
                     int var19 = var6.xK();
                     if (var19 < 0) {
                        break;
                     }

                     oL var20 = new oL(this.Dw(var19));
                     var20.RF(var18);
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
      return this.xK().q(var1);
   }

   public String q(ICodeCoordinates var1, boolean var2, boolean var3) {
      return this.xK().q(var1, var2, var3);
   }

   @Override
   public ICodeCoordinates getCodeCoordinatesFromAddress(String var1) {
      return this.xK().q(var1);
   }

   public synchronized boolean RF(Vj var1) {
      if (this.YN == null) {
         String[] var2 = this.getPropertyManager().getString("WellKnownLibraryPackages").split(",");
         this.YN = new HashSet(var2.length);
         this.Rv = false;

         for (String var6 : var2) {
            var6 = var6.trim();
            if (!var6.isEmpty()) {
               if (var6.equals("*")) {
                  this.Rv = true;
               } else {
                  this.YN.add(var6);
               }
            }
         }
      }

      String var7 = var1.getName(false);
      if (var7 != null && !var7.isEmpty()) {
         var7 = var1.q(false);
         if (var7 == null || var7.isEmpty()) {
            return false;
         } else if (this.Rv) {
            return true;
         } else {
            for (String var10 : this.YN) {
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
      Strings.ff(var1, S.L("- Dalvik parsing mode: %s\n"), bii.RF(this.qa));
      if (this.GY != null && !this.GY.isEmpty()) {
         Strings.ff(var1, S.L("- The initial input was multi-dex (%d)\n"), 1 + this.GY.size());
      } else {
         Strings.ff(var1, S.L("- The initial input was single-dex\n"));
      }

      int var2 = this.getCountOfDexEntries();
      int var3 = 1 + this.getDexEntry(var2 - 1).getFileIndex();
      Strings.ff(var1, S.L("- Dex files: %d\n"), var3);
      Strings.ff(var1, S.L("- Dex entries: %d\n"), var2);
      if (this.oW != null && this.gO != null && this.gP != null && this.za != null && this.nf != null && this.lm != null) {
         Strings.ff(var1, "\n");
         Strings.ff(
            var1,
            S.L("Dex pools:\n- Strings     : %d\n- Types       : %d\n- Prototypes  : %d\n- Field refs  : %d\n- Method refs : %d\n- Classes     : %d\n"),
            this.oW.q(),
            this.gO.q(),
            this.nf.q(),
            this.gP.q(),
            this.za.q(),
            this.lm.q()
         );
      }

      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (bjn var9 : this.lm) {
         if (var9.gP() != null) {
            for (bjy var11 : var9.gP().xK()) {
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

   public boolean wF() {
      for (bjn var2 : this.lm) {
         if (var2.gP() != null) {
            for (bjy var4 : var2.gP().xK()) {
               if (var4.isNative()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public int If() {
      int var1 = 0;

      for (bjn var3 : this.lm) {
         if (var3.gP() != null) {
            for (bjy var5 : var3.gP().xK()) {
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
         if (this.Yp == null) {
            String var3 = this.getPropertyManager().getString("AndroidJavadocRoot");
            if (!Strings.isBlank(var3) && (this.Gu == null || !this.Gu.equals(var3))) {
               this.Gu = var3;

               try {
                  Net var4 = ((zJ)RuntimeProjectUtil.findProject(this).getEnginesContext()).xK();
                  this.Yp = new Uz(this, var3, var4);
                  var1.add(this.Yp);
               } catch (Exception var6) {
                  ui.error(
                     S.L(
                        "The javadoc contribution for a Dex unit cannot be created.\nPlease update your properties, this javadoc root location seems illegal: %s"
                     ),
                     var3
                  );
                  ui.catching(var6);
               }
            } else {
               ui.debug(S.L("This javadoc root location is illegal: %s"), var3);
            }
         }

         if (this.Tq == null) {
            this.Tq = new EE(this);
            var1.add(this.Tq);
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
            return new Bu(this);
         } catch (IOException var2) {
            ui.error(S.L("Cannot generate quick state for unit %s"), this);
            ui.catchingSilent(var2);
            return null;
         }
      }
   }

   public boolean xK(int var1, String var2) {
      return this.q(var1, var2, null, 0);
   }

   public boolean q(int var1, String var2, String var3, int var4) {
      return this.Hk == null ? false : this.Hk.q(var1, var2, var3, var4);
   }

   public boolean Dz() {
      return this.getPropertyManager().getBoolean("JarLibraryEnabled");
   }

   public String mI() {
      return this.getPropertyManager().getString("JarLibraryClasspath");
   }

   public String jq() {
      return this.getPropertyManager().getString("JarLibraryFolder");
   }

   public IJLSTypeAdapter q(boolean var1, boolean var2) {
      bkp var3 = new bkp(this, false, var1);
      bko var4 = null;
      if (var2) {
         var4 = new bko(this.ui(), false);
         var4.q(true);
      }

      return new bkq(var3, var4);
   }

   public bec ui() {
      bec var1 = new bec();
      if (this.Dz()) {
         if (!Strings.isBlank(this.mI())) {
            var1.q(this.mI());
         } else {
            String var2 = this.jq();
            if (Strings.isBlank(var2)) {
               var2 = IO.expandPath("~/.jeb-android-libraries");
            }

            File var3 = new File(var2);
            if (!var3.exists()) {
               var3.mkdirs();
            }

            this.q(var3);

            for (File var5 : IO.listFiles(var3)) {
               if (var5.getName().endsWith(".jar")) {
                  var1.q(var5);
               }
            }
         }
      }

      return var1;
   }

   private void q(File var1) {
      String var2 = "android-classes.jar";
      File var3 = new File(var1, var2);
      if (!var3.exists() || var3.length() != AssetManager.getAssetSize(var2)) {
         try (
            InputStream var4 = AssetManager.getAsset(var2);
            FileOutputStream var5 = new FileOutputStream(var3, false);
         ) {
            IO.copy(var4, var5);
         } catch (IOException var12) {
            ui.error(S.L("Cannot write file: %s"), var3.getPath());
            throw new RuntimeException(var12);
         }
      }
   }

   public bkz TX() {
      if (this.br == null) {
         synchronized (this) {
            if (this.br == null) {
               bkz var2 = bkz.q;

               try {
                  var2 = new bkz(this);
               } catch (Throwable var5) {
                  ui.catching(var5, S.L("Cannot build the typegraph"));
               }

               this.br = var2;
            }
         }
      }

      return this.br;
   }

   public bkt Rr() {
      if (this.tW == null) {
         synchronized (this) {
            if (this.tW == null) {
               int var2 = this.getPropertyManager().getInteger("CallgraphAsyncBuildGracePeriod");
               int var3 = this.getPropertyManager().getInteger("CallgraphAsyncRecDetGracePeriod");
               bkt var4 = new bkt(this, var2, var3);
               this.tW = var4;
            }
         }
      }

      return this.tW;
   }

   public bks EB() {
      if (this.ZA == null) {
         synchronized (this) {
            if (this.ZA == null) {
               String var2 = this.getPropertyManager().getString("ContextInfoDb");
               bks var3 = new bks(var2);
               this.ZA = var3;
            }
         }
      }

      return this.ZA;
   }

   public bkv Xo() {
      if (this.Ov == null) {
         synchronized (this) {
            if (this.Ov == null) {
               int var2 = this.getPropertyManager().getInteger("CIDBMethodCountThresholdNoRegen");
               int var3 = this.getPropertyManager().getInteger("CIDBAsyncGenGracePeriod");
               bkv var4 = new bkv(this, var2, var3);
               this.Ov = var4;
            }
         }
      }

      return this.Ov;
   }

   @Override
   public IDexDecompilerUnit getDecompiler() {
      return (IDexDecompilerUnit)DecompilerHelper.getDecompiler(this, true);
   }

   @Override
   public IInputLocation addressToLocation(String var1) {
      ICodeCoordinates var2 = this.xK().q(var1);
      if (var2 instanceof InstructionCoordinates) {
         int var3 = ((InstructionCoordinates)var2).getMethodId();
         int var4 = ((InstructionCoordinates)var2).getOffset();
         bjp var5 = this.gO(var3);
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
               if (var12 > this.lm().size()) {
                  return null;
               }

               var10 = (IInput)this.lm().get(var12 - 1);
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
      if (this.tW != null) {
         this.tW.q();
      }

      if (this.Ov != null) {
         this.Ov.q();
      }
   }

   @Ser
   public static class CU {
      private static final int oW = 10000;
      public static final int q = 1;
      public static final int RF = 2;
      public static final int xK = 3;
      public static final int Dw = 4;
      public static final int Uv = 5;
      @SerId(1)
      private List gO = new ArrayList();
      @SerId(2)
      private boolean nf;

      public boolean q() {
         return this.gO == null;
      }

      public boolean q(int var1, String var2, String var3, int var4) {
         if (this.gO == null) {
            return false;
         } else if (this.gO.size() >= 10000) {
            this.gO = null;
            return false;
         } else {
            this.gO.add(new bK.CU.eo(var1, var2, var3, var4));
            return true;
         }
      }

      public List RF() {
         return this.gO == null ? Collections.emptyList() : this.gO;
      }

      @Override
      public String toString() {
         return this.gO == null ? "<aborted-history>" : this.gO.toString();
      }

      @Ser
      public static class eo {
         @SerId(1)
         int q;
         @SerId(2)
         String RF;
         @SerId(3)
         String xK;
         @SerId(4)
         int Dw;

         eo(int var1, String var2, String var3, int var4) {
            this.q = var1;
            this.RF = var2;
            this.xK = var3;
            this.Dw = var4;
         }

         @Override
         public String toString() {
            return Strings.ff("%d:%s:%s:0x%X", this.q, this.RF, this.xK, this.Dw);
         }
      }
   }

   private class eo {
      public static final int q = 5;
      public static final int RF = 10;
      public static final int xK = 20;
      public static final int Dw = 100;
      int Uv;
      String oW;
      int gO = 0;
      int nf = 0;
      int gP = 0;
      int za = 0;

      eo(int var2, String var3) {
         this.Uv = var2;
         if ("*".equals(var3)) {
            var3 = null;
         }

         this.oW = var3;
      }

      private int q() {
         int var1 = 0;

         for (bjn var3 : bK.this.oQ()) {
            if (this.oW == null || var3.getSignature().startsWith(this.oW)) {
               if (!var3.isRenamed()) {
                  var1++;
               }

               for (bjo var5 : var3.getFields()) {
                  if ((this.oW == null || var5.getSignature().startsWith(this.oW)) && !var5.isRenamed()) {
                     var1++;
                  }
               }

               for (bjp var7 : var3.getMethods()) {
                  if ((this.oW == null || var7.getSignature().startsWith(this.oW)) && !var7.isRenamed()) {
                     var1++;
                  }
               }
            }
         }

         return var1;
      }

      boolean q(boolean var1) {
         this.gO = this.q();

         for (bjn var3 : bK.this.oQ()) {
            if (var1 && Thread.interrupted()) {
               return false;
            }

            if (this.oW == null || var3.getSignature().startsWith(this.oW)) {
               if (!var3.isRenamed()) {
                  this.nf++;
                  String var4 = var3.getName(false);
                  bK.ui.trace("%d/%d: auto-renaming class: %s", this.nf, this.gO, var4);
                  String var5 = this.q(var4, var3);
                  if (var5 != null) {
                     if (var3.q(var5, false)) {
                        this.gP++;
                     } else {
                        this.za++;
                     }
                  }
               }

               label110:
               for (bjo var13 : var3.getFields()) {
                  if (var1 && Thread.interrupted()) {
                     return false;
                  }

                  if ((this.oW == null || var13.getSignature().startsWith(this.oW)) && !var13.isRenamed()) {
                     this.nf++;
                     String var6 = var13.getName(false);
                     bK.ui.trace("%d/%d: auto-renaming field: %s", this.nf, this.gO, var6);
                     String var7 = this.q(var6, var13);
                     if (var7 != null) {
                        bkx var8 = new bkx(bK.this);

                        for (int var10 : var8.q(var13, false)) {
                           if (!bK.this.oW(var10).isInternal()) {
                              continue label110;
                           }
                        }

                        if (var13.q(var7, false)) {
                           this.gP++;
                        } else {
                           this.za++;
                        }
                     }
                  }
               }

               label143:
               for (bjp var14 : var3.getMethods()) {
                  if (var1 && Thread.interrupted()) {
                     return false;
                  }

                  if ((this.oW == null || var14.getSignature().startsWith(this.oW)) && !var14.isRenamed()) {
                     this.nf++;
                     String var15 = var14.getName(false);
                     bK.ui.trace("%d/%d: renaming method: %s", this.nf, this.gO, var15);
                     if (!Strings.isContainedIn(var15, "<init>", "<clinit>")) {
                        String var16 = this.q(var15, var14);
                        if (var16 != null) {
                           bky var17 = new bky(bK.this);

                           for (int var19 : var17.q(var14, false)) {
                              if (!bK.this.gO(var19).isInternal()) {
                                 continue label143;
                              }
                           }

                           if (var14.q(var16, false)) {
                              this.gP++;
                           } else {
                              this.za++;
                           }
                        }
                     }
                  }
               }
            }
         }

         return true;
      }

      private String q(String var1, com.pnfsoftware.jeb.corei.parsers.dex.CU var2) {
         if (var1 != null && !var1.isEmpty()) {
            for (int var3 = 0; var3 < var1.length(); var3++) {
               char var4 = var1.charAt(var3);
               if (!this.q(var4, var3)) {
                  return this.q(var2) + var2.getIndex();
               }
            }

            return null;
         } else {
            return null;
         }
      }

      private String q(com.pnfsoftware.jeb.corei.parsers.dex.CU var1) {
         if (var1 instanceof bjn) {
            return "CLS";
         } else if (var1 instanceof bjo) {
            return "FLD";
         } else {
            return var1 instanceof bjp ? "MTH" : "ITEM";
         }
      }

      private boolean q(char var1, int var2) {
         switch (this.Uv) {
            case 5:
               if (var2 == 0) {
                  return Character.isJavaIdentifierStart(var1);
               } else {
                  if (var2 > 0) {
                     return Character.isJavaIdentifierPart(var1);
                  }

                  return false;
               }
            case 10:
               return var1 > ' ' && var1 < 127;
            case 20:
               if (var1 >= '0' && var1 <= '9') {
                  return var2 > 0;
               } else {
                  if (var1 != '$' && var1 != '_' && (var1 < 'A' || var1 > 'Z') && (var1 < 'a' || var1 > 'z')) {
                     return false;
                  }

                  return true;
               }
            case 100:
               return false;
            default:
               return true;
         }
      }

      private void q(StringBuilder var1, char var2) {
         Strings.ff(var1, "u%04X", Integer.valueOf(var2));
      }
   }

   private class nI {
      private List RF;
      private int xK;
      private Predicate Dw;

      nI(int var2, Predicate var3) {
         if (var2 < 0) {
            var2 = Integer.MAX_VALUE;
         }

         this.xK = var2;
         this.Dw = var3;
      }

      public List q() {
         if (this.RF == null) {
            this.RF = new ArrayList(bK.this.lm.q() + bK.this.za.q() + bK.this.gP.q());
            if (!this.q(bK.this.lm)) {
               return this.RF;
            }

            if (!this.q(bK.this.za)) {
               return this.RF;
            }

            if (!this.q(bK.this.gP)) {
               return this.RF;
            }
         }

         return this.RF;
      }

      private boolean q(bkb var1) {
         for (com.pnfsoftware.jeb.corei.parsers.dex.CU var3 : var1) {
            if (var3.isInternal()) {
               if (!this.q(var3.getSignature())) {
                  return false;
               }

               if (var3.isRenamed() && !this.q(var3.getSignature(false))) {
                  return false;
               }
            }
         }

         return true;
      }

      boolean q(String var1) {
         if (!Strings.isBlank(var1) && (this.Dw == null || this.Dw.test(var1))) {
            this.RF.add(var1);
            if (this.RF.size() >= this.xK) {
               return false;
            }
         }

         return true;
      }
   }
}
