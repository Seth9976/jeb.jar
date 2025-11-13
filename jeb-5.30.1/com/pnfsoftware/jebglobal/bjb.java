package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.CoordinatesConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.code.CodeLine;
import com.pnfsoftware.jeb.core.output.code.CommentGenerator;
import com.pnfsoftware.jeb.core.output.code.coordinates.ClassCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.CodeCoordinatesUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.FieldCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.output.text.IAnchor;
import com.pnfsoftware.jeb.core.output.text.IBinaryRepresentation;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.impl.Coordinates;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeSelection;
import com.pnfsoftware.jeb.core.units.code.android.ApkStringResHelper;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.ParametersInfo;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.DalvikParserError;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexMethodHandleType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstructionArrayData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstructionSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationElement;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationItem;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugInfo;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugLine;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugVariable;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.render.DexDisassemblyProperties;
import com.pnfsoftware.jeb.core.units.code.android.render.IDexDisassemblyDocument;
import com.pnfsoftware.jeb.core.units.code.android.render.IDexItemToAnchor;
import com.pnfsoftware.jeb.core.units.impl.Comment;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Bytes;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class bjb extends CodeDocument implements IDexDisassemblyDocument {
   private static final ILogger EB = GlobalLog.getLogger(bjb.class);
   public static final String q = "#start#";
   public static final String RF = "#end";
   public static final String xK = "END";
   public static final boolean Dw = true;
   public static final boolean Uv = false;
   public static final boolean oW = true;
   public static final boolean gO = false;
   public static final boolean nf = false;
   public static final boolean gP = true;
   public static final boolean za = false;
   public static final int lm = 0;
   public static final int zz = 0;
   public static final boolean JY = false;
   public static final boolean HF = false;
   public static final boolean LK = false;
   public static final boolean io = true;
   public static final boolean qa = false;
   private boolean Xo;
   private boolean Bu;
   private boolean IN;
   private boolean rL;
   private boolean eJ;
   private boolean YN;
   private boolean Rv;
   private int zx;
   private int ZT;
   private boolean Ri;
   private boolean GY;
   private boolean Wx;
   private boolean AB;
   private boolean CY;
   private DexDisassemblyProperties WI;
   private String Tq = "";
   private boolean Yp;
   private com.pnfsoftware.jeb.corei.parsers.dex.bK Gu;
   private IPropertyManager nY;
   private IEventListener lF;
   private IEventListener nq;
   private bje NX;
   private biy br;
   private ConcurrentHashMap tW;
   private String ZA;
   private String Ov;
   private boolean Lj = false;
   private ApkStringResHelper nv;
   public static final String Hk = "ShowAddresses";
   public static final String Me = "ShowBytecode";
   public static final String PV = "ShowAnnotations";
   public static final String oQ = "ShowDebugDirectives";
   public static final String xW = "ShowLineNumbers";
   public static final String KT = "UsePForParameters";
   public static final String Gf = "SmaliCompatibility";
   public static final String Ef = "ClassSeparatorLength";
   public static final String cC = "MethodSeparatorLength";
   public static final String sH = "ShowSpaceBetweenBlocks";
   public static final String CE = "ShowInstructionsInGaps";
   public static final String wF = "GenerateCatchDirectivesAtMethodEnd";
   public static final String If = "ShowActualCallsites";
   public static final String Dz = "ShowOriginalNames";
   public static final String mI = "PreferredBaseForIntegers";
   int jq;
   int ui;
   int TX;
   int Rr;
   private Map LL = new HashMap();
   private List PQ = new ArrayList();
   private Map fQ = new HashMap();
   private static final int fi = 0;
   private static final int bl = 1;
   private static final int jb = 2;
   private transient bjb.eo pQ;

   public bjb(com.pnfsoftware.jeb.corei.parsers.dex.bK var1) {
      if (var1 == null) {
         throw new JebRuntimeException("Cannot generate the disassembly document of a null DEX unit");
      } else if (!var1.isProcessed()) {
         throw new JebRuntimeException("The DEX unit must be processed before generating the disassembly document");
      } else {
         this.Gu = var1;
         this.br = new biy();
         this.tW = new ConcurrentHashMap();
         this.q();
         this.q(false);
         var1.addListener(this.lF = new bjc(this));
         this.nY = var1.getPropertyManager();
         this.nY.addListener(this.nq = new bjd(this));
         if (var1.getParent() instanceof com.pnfsoftware.jeb.corei.parsers.apk.ej) {
            com.pnfsoftware.jeb.corei.parsers.apk.ej var2 = (com.pnfsoftware.jeb.corei.parsers.apk.ej)var1.getParent();
            this.nv = var2.q();
         }
      }
   }

   private void q() {
      List var1 = this.Gu.getClasses();
      this.br.q();
      long var2 = 0L;
      this.br.q(var2++);

      for (bjn var5 : var1) {
         this.br.q(var2++, var5);
         List var6 = var5.getFields();
         if (var6 != null) {
            for (bjo var8 : var6) {
               this.br.q(var2++, var8);
            }
         }

         List var14 = var5.getMethods();
         if (var14 != null) {
            for (bjp var9 : var5.getMethods()) {
               bip var10 = var9.RF().q();
               if (var10 == null) {
                  this.br.q(var2++, var9);
               } else if (var10.getControlFlowGraph() != null) {
                  for (BasicBlock var12 : var10.getControlFlowGraph()) {
                     this.br.q(var2++, var9, var12);
                  }
               }
            }
         }
      }
   }

   public static void q(IPropertyDefinitionManager var0) {
      IPropertyDefinitionGroup var1 = var0.addGroup("text");
      var1.addDefinition("ShowAddresses", PropertyTypeBoolean.create(true), S.L("Display addresses of items"));
      var1.addDefinition("ShowBytecode", PropertyTypeBoolean.create(false), S.L("Display the instruction bytecode"));
      var1.addDefinition("ShowAnnotations", PropertyTypeBoolean.create(true), S.L("Show the DEX annotations"));
      var1.addDefinition("ShowDebugDirectives", PropertyTypeBoolean.create(false), S.L("Show the debug directives (metadata)"));
      var1.addDefinition("ShowLineNumbers", PropertyTypeBoolean.create(false), S.L("Show the source line numbers (metadata)"));
      var1.addDefinition("UsePForParameters", PropertyTypeBoolean.create(true), S.L("Use p0,p1,... instead of v0,v1,... for variables"));
      var1.addDefinition("SmaliCompatibility", PropertyTypeBoolean.create(false), S.L("Generate assembly code compatible with Smali"));
      var1.addDefinition("ClassSeparatorLength", PropertyTypeInteger.createPositiveOrZero(0), S.L("Length of the class separator line in characters"));
      var1.addDefinition("MethodSeparatorLength", PropertyTypeInteger.createPositiveOrZero(0), S.L("Length of the method separator line in characters"));
      var1.addDefinition("ShowSpaceBetweenBlocks", PropertyTypeBoolean.create(false), S.L("Insert a blank line between basic blocks"));
      var1.addDefinition(
         "ShowInstructionsInGaps",
         PropertyTypeBoolean.create(false),
         S.L("Parse byte gaps (slack space or unused data bytes that can be present in a method body) and display those bytes as Dalvik instructions")
      );
      var1.addDefinition(
         "GenerateCatchDirectivesAtMethodEnd",
         PropertyTypeBoolean.create(false),
         S.L("Generate .catch/.catchall directives at the EOM instead of after a try-block")
      );
      var1.addDefinition(
         "ShowActualCallsites",
         PropertyTypeBoolean.create(true),
         S.L(
            "Determine and display the actual callsite for method invocations. Automatic inline comments will be added when the determined callsite differ from the method reference."
         )
      );
      var1.addDefinition(
         "ShowOriginalNames", PropertyTypeBoolean.create(false), S.L("Display the original descriptor names of classes/methods/fields as comments")
      );
      var1.addDefinition(
         "PreferredBaseForIntegers",
         PropertyTypeSelection.Builder.create()
            .addDefaultEntry(0, S.L("Auto"), S.L("Automatically determine the best base for rendering"))
            .addEntry(8, S.L("Octal"), S.L("Prefer base 8"))
            .addEntry(10, S.L("Decimal"), S.L("Prefer base 10"))
            .addEntry(16, S.L("Hexadecimal"), S.L("Prefer base 16"))
            .build(),
         S.L(
            "Select the preferred base for rendering. Regardless of this selection, the base to render literals can be overridden by the user on a discrationary basis. This option is also used by dexdec when rendering decompiled Java source."
         )
      );
   }

   private void q(boolean var1) {
      IPropertyManager var2 = this.Gu.getPropertyManager();
      if (this.WI != null && this.WI.getShowAddresses() != null) {
         this.Xo = this.WI.getShowAddresses();
      } else {
         this.Xo = var2.getBoolean("ShowAddresses", true);
      }

      if (this.WI != null && this.WI.getShowBytecode() != null) {
         this.Bu = this.WI.getShowBytecode();
      } else {
         this.Bu = var2.getBoolean("ShowBytecode", false);
      }

      if (this.WI != null && this.WI.getShowAnnotations() != null) {
         this.IN = this.WI.getShowAnnotations();
      } else {
         this.IN = var2.getBoolean("ShowAnnotations", true);
      }

      if (this.WI != null && this.WI.getShowDebugDirectives() != null) {
         this.rL = this.WI.getShowDebugDirectives();
      } else {
         this.rL = var2.getBoolean("ShowDebugDirectives", false);
      }

      if (this.WI != null && this.WI.getShowLineNumbers() != null) {
         this.eJ = this.WI.getShowLineNumbers();
      } else {
         this.eJ = var2.getBoolean("ShowLineNumbers", false);
      }

      if (this.WI != null && this.WI.getUsePForParameters() != null) {
         this.YN = this.WI.getUsePForParameters();
      } else {
         this.YN = var2.getBoolean("UsePForParameters", true);
      }

      if (this.WI != null && this.WI.getSmaliCompatibility() != null) {
         this.Rv = this.WI.getSmaliCompatibility();
      } else {
         this.Rv = var2.getBoolean("SmaliCompatibility", false);
      }

      if (this.WI != null && this.WI.getClassSeparatorLength() != null) {
         this.zx = this.WI.getClassSeparatorLength();
      } else {
         this.zx = var2.getInteger("ClassSeparatorLength", 0);
      }

      this.ZA = this.zx <= 0 ? null : Strings.generate('=', this.zx);
      if (this.WI != null && this.WI.getMethodSeparatorLength() != null) {
         this.ZT = this.WI.getMethodSeparatorLength();
      } else {
         this.ZT = var2.getInteger("MethodSeparatorLength", 0);
      }

      this.Ov = this.ZT <= 0 ? null : Strings.generate('-', this.ZT);
      if (this.WI != null && this.WI.getShowSpaceBetweenBlocks() != null) {
         this.Ri = this.WI.getShowSpaceBetweenBlocks();
      } else {
         this.Ri = var2.getBoolean("ShowSpaceBetweenBlocks", false);
      }

      if (this.WI != null && this.WI.getShowInstructionsInGaps() != null) {
         this.GY = this.WI.getShowInstructionsInGaps();
      } else {
         this.GY = var2.getBoolean("ShowInstructionsInGaps", false);
      }

      if (this.WI != null && this.WI.getGenerateCatchDirectivesAtMethodEnd() != null) {
         this.Wx = this.WI.getGenerateCatchDirectivesAtMethodEnd();
      } else {
         this.Wx = var2.getBoolean("GenerateCatchDirectivesAtMethodEnd", false);
      }

      if (this.WI != null && this.WI.getShowActualCallsites() != null) {
         this.AB = this.WI.getShowActualCallsites();
      } else {
         this.AB = var2.getBoolean("ShowActualCallsites", true);
      }

      if (this.WI != null && this.WI.getShowOriginalNames() != null) {
         this.CY = this.WI.getShowOriginalNames();
      } else {
         this.CY = var2.getBoolean("ShowOriginalNames", false);
      }

      this.RF();
      this.Tq = Strings.spaces(this.TX);
      if (this.NX != null) {
         this.NX.setPaddingString(this.Tq);
      }

      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   private void RF() {
      this.jq = 0;
      this.ui = this.jq;
      if (this.Xo) {
         this.ui += 10;
      }

      this.TX = this.ui;
      if (this.Bu) {
         this.TX += 24;
      }

      this.Rr = this.TX + 40;
   }

   @Override
   public DexDisassemblyProperties getPropertyOverrides() {
      return this.WI;
   }

   @Override
   public void setPropertyOverrides(DexDisassemblyProperties var1) {
      this.WI = var1;
      this.q(true);
   }

   @Override
   public void dispose() {
      if (!this.Yp) {
         super.dispose();
         this.tW.clear();
         this.Gu.removeListener(this.lF);
         this.nY.removeListener(this.nq);
         this.Yp = true;
      }
   }

   @Override
   public long getAnchorCount() {
      return this.br.size();
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      int var3 = (int)var1.getAnchorId();
      if (var3 < 0 || var3 >= this.getAnchorCount()) {
         return null;
      } else if (var3 == 0) {
         return "#start#";
      } else {
         bje var4 = (bje)this.tW.get(var3);
         if (var4 == null) {
            var4 = this.q((long)var3, 1);
            if (var4 == null) {
               return null;
            }
         }

         int var5 = -1;

         for (IAnchor var7 : var4.getAnchors()) {
            if (var7.getIdentifier() == var3) {
               var5 = var7.getLineIndex();
               break;
            }
         }

         if (var5 < 0) {
            return null;
         } else {
            int var13 = var5 + var1.getLineDelta();
            if (var13 < var4.getLineCount() && var13 >= 0) {
               CodeLine var14 = var4.getLine(var13);
               ICodeCoordinates var8 = var14.getLineCoordinates();
               if (var8 == null) {
                  return null;
               } else if (var8 instanceof ClassCoordinates) {
                  int var17 = ((ClassCoordinates)var8).getClassId();
                  bjn var20 = this.Gu.nf(var17);
                  return var20.getSignature(true);
               } else if (var8 instanceof FieldCoordinates) {
                  int var16 = ((FieldCoordinates)var8).getFieldId();
                  bjo var19 = this.Gu.oW(var16);
                  return var19.getSignature(true);
               } else if (var8 instanceof MethodCoordinates) {
                  int var15 = ((MethodCoordinates)var8).getMethodId();
                  bjp var18 = this.Gu.gO(var15);
                  return ((MethodCoordinates)var8).isEndFlag() ? var18.getSignature(true) + "+END" : var18.getSignature(true);
               } else if (var8 instanceof InstructionCoordinates) {
                  int var9 = ((InstructionCoordinates)var8).getMethodId();
                  bjp var10 = this.Gu.gO(var9);
                  String var11 = var10.getSignature(true);
                  int var12 = ((InstructionCoordinates)var8).getOffset();
                  return var11 + "+" + Formatter.toHexString(var12, true) + "h";
               } else {
                  return "?";
               }
            } else {
               return null;
            }
         }
      }
   }

   public Coordinates q(String var1, CoordinatesConversionPrecision var2) {
      Coordinates var3 = this.RF(var1, var2);
      if (var3 == null && var1 != null && Strings.indexOf2(var1, '/', ';') == -1) {
         if (var1.indexOf(46) >= 0) {
            var1 = "L" + var1.replace('.', '/') + ";";
            var3 = this.RF(var1, var2);
         } else {
            bjn var4 = this.Gu.oQ().RF(var1);
            if (var4 != null) {
               var3 = this.RF(var4.getSignature(), var2);
            }
         }
      }

      return var3 == null && "#start#".equals(var1) ? new Coordinates(0L) : var3;
   }

   public Coordinates RF(String var1, CoordinatesConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         if (var2 == null) {
            var2 = CoordinatesConversionPrecision.BEST;
         }

         if (!var1.contains("->")) {
            bjn var21 = this.Gu.oQ().q(var1);
            if (var21 == null) {
               return null;
            } else {
               Long var23 = this.br.fromClass(var21);
               return var23 == null ? null : new Coordinates(var23, 0, 0);
            }
         } else if (var1.contains(":")) {
            bjo var20 = this.Gu.Me().q(var1);
            if (var20 != null && var20.isInternal()) {
               Long var22 = this.br.fromField(var20);
               return var22 == null ? null : new Coordinates(var22, 0, 0);
            } else {
               return null;
            }
         } else if (!var1.contains("(")) {
            return null;
         } else {
            int var3 = -1;
            int var4 = var1.indexOf(43);
            boolean var5 = false;
            if (var4 >= 0) {
               String var6 = var1.substring(var4 + 1);
               if (var6.toUpperCase().equals("END")) {
                  var5 = true;
               } else {
                  var3 = Conversion.stringToInt(var6, -1);
               }

               var1 = var1.substring(0, var4);
            }

            if (var1.endsWith("#end")) {
               var5 = true;
               var1 = var1.substring(0, var1.length() - "#end".length());
            }

            bjp var24 = this.Gu.PV().q(var1);
            if (var24 != null && var24.isInternal()) {
               if (var3 <= 0) {
                  Long var7 = this.br.fromMethod(var24);
                  if (var7 != null) {
                     return new Coordinates(var7, 0, 0);
                  }

                  var3 = 0;
               }

               bip var25 = var24.RF().q();
               if (var25 != null && var25.getControlFlowGraph() != null) {
                  BasicBlock var8 = null;
                  Object var9 = null;
                  if (var5) {
                     var8 = var25.getControlFlowGraph().getLast();
                     var9 = new MethodCoordinates(var24.getIndex(), true);
                  } else {
                     Couple var10 = var25.getControlFlowGraph().getInstructionLocation(var3, false);
                     if (var10 != null) {
                        var8 = (BasicBlock)var10.getFirst();
                        var3 = (int)((IDalvikInstruction)var8.get((Integer)var10.getSecond())).getOffset();
                        var9 = new InstructionCoordinates(var24.getIndex(), var3);
                     }
                  }

                  if (var8 == null) {
                     return null;
                  } else {
                     Long var26 = this.br.fromInstruction(var24, var8);
                     if (var26 == null) {
                        return null;
                     } else {
                        bje var11 = this.q(var26, 1);
                        int var12 = 0;
                        Coordinates var13 = null;

                        for (CodeLine var15 : var11.getLines()) {
                           ICodeCoordinates var16 = var15.getLineCoordinates();
                           if (var9.equals(var16)) {
                              List var17 = (List)var15.getItems()
                                 .stream()
                                 .filter(var0 -> var0.getClassId() != ItemClassIdentifiers.COMMENT)
                                 .collect(Collectors.toList());
                              if (var17.isEmpty()) {
                                 var12++;
                                 continue;
                              }

                              Coordinates var18 = new Coordinates(var26, var12, 0);
                              if (var2 == CoordinatesConversionPrecision.FIRST) {
                                 return var18;
                              }

                              if (var2 == CoordinatesConversionPrecision.BEST) {
                                 if (!((List)var17.stream().filter(var0 -> var0.getClassId() == ItemClassIdentifiers.MNEMONIC).collect(Collectors.toList()))
                                    .isEmpty()) {
                                    return var18;
                                 }

                                 var13 = var18;
                              } else if (var2 == CoordinatesConversionPrecision.LAST) {
                                 var13 = var18;
                              }
                           }

                           var12++;
                        }

                        return var13;
                     }
                  }
               } else {
                  return null;
               }
            } else {
               return null;
            }
         }
      }
   }

   @Override
   public ITextDocumentPart getItemDisassembly(ICodeCoordinates var1) {
      Long var2 = this.br.from(var1);
      return var2 == null ? null : this.q(var2, 1, 0);
   }

   public bje q(long var1, int var3, int var4) {
      int var5 = (int)var1;
      if (var5 >= 0 && var5 < this.br.size()) {
         synchronized (this) {
            this.NX = new bje(var5, this.Tq, this.Rv);
            if (var3 > 0) {
               for (int var7 = var5; var7 < this.br.size() && this.NX.getLineCount() < var3; var7++) {
                  this.q((long)var7);
               }
            }

            if (var4 > 0) {
               int var12 = this.NX.getLineCount() + var4;
               bje var8 = this.NX;

               for (int var9 = var5 - 1; var9 >= 0 && var8.getLineCount() < var12; var9--) {
                  this.NX = new bje(var9, this.Tq, this.Rv);
                  this.q((long)var9);
                  var8.prependCodePart(this.NX);
               }

               this.NX = var8;
            }

            this.tW.clear();

            for (IAnchor var14 : this.NX.getAnchors()) {
               this.tW.put((int)var14.getIdentifier(), this.NX);
            }

            return this.NX;
         }
      } else {
         return null;
      }
   }

   public bje q(long var1, int var3) {
      return this.q(var1, var3, 0);
   }

   private void q(long var1) {
      ICodeCoordinates var3 = this.br.get(var1);
      if (var1 == 0L) {
         try {
            this.NX.registerAnchor("header");
            bjg var4 = new bjg(this.Gu, this.NX);
            var4.q(false);
            var4.q();
         } catch (Exception var7) {
            JebCoreService.notifySilentExceptionToClient(var7);
         }

         this.NX.eol();
      } else if (var3 instanceof ClassCoordinates) {
         bjn var8 = this.Gu.nf(((ClassCoordinates)var3).getClassId());
         this.q(var8);
      } else if (var3 instanceof FieldCoordinates) {
         bjo var9 = this.Gu.oW(((FieldCoordinates)var3).getFieldId());
         this.q(var9);
      } else if (var3 instanceof MethodCoordinates) {
         bjp var10 = this.Gu.gO(((MethodCoordinates)var3).getMethodId());
         this.q(var10);
      } else {
         if (!(var3 instanceof InstructionCoordinates)) {
            throw new JebRuntimeException(
               Strings.ff("Unsupported anchor: id=%d, object=%s (%s)", var1, var3, var3 == null ? "null" : var3.getClass().getName())
            );
         }

         bjp var11 = this.Gu.gO(((InstructionCoordinates)var3).getMethodId());
         int var5 = ((InstructionCoordinates)var3).getOffset();
         BasicBlock var6 = var11.RF().q().getControlFlowGraph().getBlockAt((long)var5);
         this.q(var11, var6);
      }
   }

   private boolean q(ICodeCoordinates var1) {
      return this.q(var1, null);
   }

   private boolean q(ICodeCoordinates var1, String var2) {
      Comment var3 = this.Gu.getCommentManager().getComment2(var1);
      CommentGenerator var4 = new CommentGenerator(this.NX, "#");
      var4.setMargin(this.Rr);
      return var4.genInline(var1, var3, 0, 2, var2);
   }

   private boolean RF(ICodeCoordinates var1) {
      Comment var2 = this.Gu.getCommentManager().getComment2(var1);
      if (var2 != null && var2.getPre() != null) {
         if (var1 instanceof InstructionCoordinates) {
            this.q(-1, null);
         }

         CommentGenerator var3 = new CommentGenerator(this.NX, "#");
         return var3.genPre(var1, var2, null);
      } else {
         return false;
      }
   }

   private void q(bjn var1) {
      this.NX.registerAnchor(var1.getSignature(false));
      ClassCoordinates var2 = new ClassCoordinates(var1.getIndex());
      this.NX.recordLineCoordinates(var2);
      if (this.ZA != null) {
         this.NX.appendCommentAuto(this.ZA);
         this.NX.eol();
      }

      this.RF(var2);
      this.NX.q(bja.q);
      this.NX.space();
      q(this.NX, 0, var1.getAccessFlags(), -1);
      this.NX.q(this.Gu, var1.getClassTypeIndex(), true);
      this.q(var2);
      this.NX.eol(var2);
      this.q((com.pnfsoftware.jeb.corei.parsers.dex.CU)var1);
      this.NX.q(bja.RF);
      this.NX.space();
      int var3 = var1.getSuperTypeIndex();
      if (var3 >= 0) {
         this.NX.Uv(this.Gu, var3);
      } else {
         String var4 = var1.getSupertypeSignature(true);
         this.NX.appendAndRecord(var4, ItemClassIdentifiers.CLASS_NAME);
      }

      this.NX.eol();
      if (this.rL) {
         this.NX.q(biz.q);
         this.NX.space();
         int var9 = var1.getSourceStringIndex();
         if (var9 != -1) {
            this.NX.q(this.Gu, var9);
         }

         this.NX.eol();
      }

      this.NX.eol();
      int[] var10 = var1.getInterfaceTypeIndexes();
      if (var10.length > 0) {
         for (int var8 : var10) {
            this.NX.q(bja.xK);
            this.NX.space();
            this.NX.Uv(this.Gu, var8);
            this.NX.eol();
         }

         this.NX.eol();
      }

      if (this.IN) {
         List var11 = var1.za();
         if (!var11.isEmpty()) {
            for (IDexAnnotationItem var13 : var11) {
               this.q(var13);
               this.NX.eol();
            }
         }
      }

      this.NX.unrecordLineCoordinates();
   }

   private void q(bjo var1) {
      bjt var2 = var1.Dw();
      if (var2.isInternal()) {
         this.NX.registerAnchor(var1.getSignature(false));
         FieldCoordinates var3 = new FieldCoordinates(var1.getIndex());
         this.NX.recordLineCoordinates(var3);
         bju var4 = var1.RF();
         int var5 = 0;
         bjn var6 = var2.xK();
         bia[] var7 = var6.nf();

         for (bju var9 : var6.gP().q()) {
            if (var9 == var4) {
               break;
            }

            if (var9.isStatic()) {
               var5++;
            }
         }

         this.RF(var3);
         this.NX.q(bja.nf);
         this.NX.space();
         q(this.NX, 1, var4.getAccessFlags(), -1);
         this.NX.appendAndRecord(var1.getName(true), ItemClassIdentifiers.FIELD_NAME, this.Gu.q(var1), 1);
         this.NX.append(":");
         this.NX.Uv(this.Gu, var1.getFieldTypeIndex());
         if (var4.isStatic() && var5 < var7.length) {
            this.NX.append(" = ");
            this.q(var7[var5]);
         }

         this.q(var3);
         this.NX.eol(var3);
         this.q((com.pnfsoftware.jeb.corei.parsers.dex.CU)var1);
         if (this.IN) {
            List var11 = var6.q(var4.getFieldIndex());
            if (var11 != null && !var11.isEmpty()) {
               this.NX.incrementIndentationLevel();

               for (IDexAnnotationItem var10 : var11) {
                  this.q(var10);
               }

               this.NX.decrementIndentationLevel();
               this.NX.q(bja.gP);
               this.NX.eol();
            }
         }

         this.NX.eol();
         this.NX.unrecordLineCoordinates();
      }
   }

   private void q(bjp var1) {
      this.q(var1, null);
   }

   private void q(bjp var1, BasicBlock var2) {
      bjt var3 = var1.Dw();
      if (var3.isInternal()) {
         bjy var4 = var1.RF();
         bjn var5 = var3.xK();
         boolean var6;
         if (var2 == null) {
            this.NX.registerAnchor(var1.getSignature(false));
            var6 = true;
         } else if (var2.getFirstAddress() == 0L) {
            this.NX.registerAnchor(var1.getSignature(false) + "+0h");
            var6 = true;
         } else {
            var6 = false;
         }

         boolean var7;
         if (var2 != null && (var4.q() == null || var4.q().getControlFlowGraph() == null || var4.q().getControlFlowGraph().getLast() != var2)) {
            var7 = false;
         } else {
            var7 = true;
         }

         MethodCoordinates var8 = new MethodCoordinates(var1.getIndex());
         this.NX.recordLineCoordinates(var8);
         if (var6) {
            if (this.Ov != null) {
               this.NX.appendCommentAuto(this.Ov);
               this.NX.eol();
            }

            this.RF(var8);
            this.NX.q(bja.za);
            this.NX.space();
            q(this.NX, 2, var4.getAccessFlags(), -1);
            this.NX.appendAndRecord(var1.getName(true), ItemClassIdentifiers.METHOD_NAME, this.Gu.q(var1), 1);
            this.NX.q(this.Gu, var1.Uv());
            this.q(var8);
            this.NX.eol(var8);
            this.q((com.pnfsoftware.jeb.corei.parsers.dex.CU)var1);
         }

         this.NX.enablePadding();
         this.q(var5, var4, var1, var2, var6, var7);
         this.NX.disablePadding();
         if (var7) {
            this.NX.recordLineCoordinates(new MethodCoordinates(var1.getIndex(), true));
            this.NX.q(bja.lm);
            MethodCoordinates var9 = new MethodCoordinates(var1.getIndex(), true);
            this.q(var9);
            this.NX.eol(var9);
            this.NX.eol();
            this.NX.unrecordLineCoordinates();
         }

         this.NX.unrecordLineCoordinates();
      }
   }

   ParametersInfo q(bjy var1) {
      int var2 = var1.getMethodIndex();
      ParametersInfo var3 = (ParametersInfo)this.LL.get(var2);
      if (var3 == null) {
         var3 = DexUtil.getMethodParametersInfo(this.Gu, var1);
         int var4 = this.PQ.indexOf(var3);
         if (var4 >= 0) {
            this.LL.put(var2, (ParametersInfo)this.PQ.get(var4));
         } else {
            this.LL.put(var2, var3);
            this.PQ.add(var3);
         }
      }

      return var3;
   }

   public List RF(bjy var1) {
      int var2 = var1.getMethodIndex();
      Object var3 = (List)this.fQ.get(var2);
      if (var3 == null) {
         HashSet var4 = new HashSet();
         var3 = new ArrayList();
         if (var1.q() != null) {
            for (IDalvikInstruction var6 : var1.q().getInstructions()) {
               int var7 = var6.getOpcode();
               if (var7 == 43 || var7 == 44 || var7 == 38) {
                  long var8 = var6.getParameterValue(1);
                  int var10 = (int)(var6.getOffset() + var8 * 2L);
                  if (var4.add(var10)) {
                     var3.add(var6);
                  }
               }
            }
         }

         this.fQ.put(var2, var3);
      }

      return (List)var3;
   }

   private void q(bjn var1, bjy var2, bjp var3, BasicBlock var4, boolean var5, boolean var6) {
      if (var4 != null && !var5) {
         StringBuilder var7 = new StringBuilder(var3.getSignature(false)).append('+').append(Formatter.toHexString(var4.getFirstAddress(), true)).append('h');
         this.NX.registerAnchor(var7.toString());
      }

      bip var34 = var2.q();
      IDexDebugInfo var8 = var34 != null && this.rL ? var34.getDebugInfo() : null;
      ParametersInfo var9 = this.q(var2);
      if (var5) {
         if (var34 != null) {
            this.NX.q(bja.xW);
            this.NX.append(" " + var34.getRegisterCount());
            this.NX.eol();
            if (var34.hasParsingErrors()) {
               this.NX.appendCommentAuto("# " + S.L("Bytecode parsing errors!"));
               this.NX.eol();

               for (DalvikParserError var11 : var34.getParsingErrors()) {
                  this.NX.appendCommentAuto("# - " + var11);
                  this.NX.eol();
               }
            }
         }

         if (this.IN) {
            List var35 = var1.RF(var2.getMethodIndex());
            if (var35 != null && !var35.isEmpty()) {
               for (IDexAnnotationItem var12 : var35) {
                  this.q(var12);
               }
            }
         }

         boolean var36 = false;
         if (this.IN) {
            List var39 = var1.xK(var2.getMethodIndex());
            if (var39 != null && !var39.isEmpty()) {
               int[] var42 = null;
               if (var8 != null) {
                  var42 = var8.getParameterNameIndexes();
                  var36 = true;
               }

               int var13 = 0;

               for (List var15 : var39) {
                  this.NX.q(bja.zz);
                  this.NX.append(" p" + (var13 < var9.getParameterCount() ? var9.getParameterIndex(var13) : -1));
                  if (var42 != null && var13 < var42.length && var42[var13] != -1) {
                     this.NX.appendParameterSeparator();
                     this.NX.q(this.Gu, var42[var13]);
                  }

                  this.NX.eol();
                  if (var15 != null && !var15.isEmpty()) {
                     this.NX.incrementIndentationLevel();

                     for (IDexAnnotationItem var17 : var15) {
                        this.q(var17);
                     }

                     this.NX.decrementIndentationLevel();
                  }

                  this.NX.q(bja.JY);
                  this.NX.eol();
                  var13++;
               }
            }
         }

         if (var34 == null) {
            return;
         }

         if (var8 != null && !var36) {
            int var40 = 0;

            for (int var50 : var8.getParameterNameIndexes()) {
               this.NX.q(bja.zz);
               this.NX.append(" p" + (var40 < var9.getParameterCount() ? var9.getParameterIndex(var40) : -1));
               if (var50 != -1) {
                  this.NX.appendParameterSeparator();
                  this.NX.q(this.Gu, var50);
               }

               this.NX.eol();
               var40++;
            }
         }
      }

      int var37 = var34.getRegisterCount() - var9.getRegisterCount();
      CFG var41 = var34.getControlFlowGraph();
      if (var41 != null) {
         Map var44 = var34.getGaps();
         this.NX.disablePadding();
         boolean var46 = false;

         for (biw var55 : var34.Uv()) {
            if (var55.getTryAddress() == 0) {
               var46 = true;
               break;
            }
         }

         bji var49 = new bji(var34);
         bjh var52 = new bjh(var34, this.Gu, this.NX);
         var52.q(var49);
         BasicBlock var54 = null;
         int var56 = 0;
         if (var4 != null) {
            for (BasicBlock var19 : var41) {
               if (var19.getFirstAddress() == var4.getFirstAddress()) {
                  var54 = var19;
                  break;
               }

               var56++;
            }
         } else {
            var54 = var41.get(0);
         }

         InstructionCoordinates var57 = new InstructionCoordinates(var3.getIndex(), (int)((IDalvikInstruction)var54.get(0)).getOffset());
         this.NX.recordLineCoordinates(var57);
         if (this.Ri && var56 > 0) {
            this.NX.eol();
         }

         if (var54.allinsize() >= 1 || var56 == 0 && var46) {
            int var60 = (int)((IDalvikInstruction)var54.get(0)).getOffset();
            var52.q(var34, var60, true);
            bji.eo var20 = var49.q(var60);
            if (var20 != null && !var20.Dw.isEmpty()) {
               TreeSet var21 = new TreeSet();

               for (biv var23 : var20.Dw) {
                  if (!var23.isTyped()) {
                     var21.add("*");
                  } else {
                     var21.add(this.Gu.Dw(var23.RF()).getSignature());
                  }
               }

               if (!var21.isEmpty()) {
                  String var70 = Strings.ff(S.L("used for: %s"), Strings.join(", ", var21));
                  CommentGenerator var76 = new CommentGenerator(this.NX, "#");
                  var76.genInline(var57, var70, true);
               }
            }

            this.NX.eol();
         }

         this.NX.unrecordLineCoordinates();

         for (IDalvikInstruction var63 : var54) {
            this.q(var63, var3, var37, var8, var52, var34);
         }

         int var62 = (int)var54.getEndAddress();
         byte[] var64 = (byte[])var44.get(var62);
         if (var64 != null) {
            this.q(true, var3, var62, var64, var37, var8, var52, var34);
         }

         if (!this.Wx) {
            var57 = new InstructionCoordinates(var3.getIndex(), (int)var54.getLastAddress());
            this.NX.recordLineCoordinates(var57);
            int var65 = (int)var54.getEndAddress();
            bji.eo var71 = var49.q(var65);
            if (var71 != null) {
               this.q(var34, var52, var71.xK);
            }

            this.NX.unrecordLineCoordinates();
         }

         if (var6) {
            if (var41.size() >= 1 && var41.get(var41.size() - 1).size() >= 1) {
               IDalvikInstruction var66 = (IDalvikInstruction)var41.get(var41.size() - 1).getLast();
               int var72 = (int)var66.getOffset() + var66.getSize();
               boolean var77 = false;

               for (biw var25 : var34.getExceptionItems()) {
                  if (var25.RF() == var72) {
                     var77 = true;
                     break;
                  }
               }

               if (var77) {
                  var52.q(var34, var72, true);
                  this.NX.eol();
               }
            }

            var57 = new InstructionCoordinates(var3.getIndex(), (int)var54.getLastAddress());
            this.NX.recordLineCoordinates(var57);
            if (this.Wx) {
               this.q(var34, var52, var34.getExceptionItems());
            }

            this.NX.enablePadding();

            for (IDalvikInstruction var73 : this.RF(var2)) {
               int var78 = var73.getOpcode();
               if (var78 == 43) {
                  this.NX.disablePadding();
                  IDalvikInstructionSwitchData var82 = var73.getSwitchData();
                  var52.q(var34, var82.getOffset(), true);
                  this.NX.eol();
                  this.q(var82.getOffset(), null);
                  this.NX.q(bja.io);
                  int[][] var85 = var82.getElements();
                  int var87 = 0;
                  if (var85.length >= 1) {
                     var87 = var85[0][0];
                     this.NX.space();
                     this.q(var87);
                  }

                  this.NX.eol();
                  this.NX.enablePadding();
                  this.NX.incrementIndentationLevel();
                  int var89 = var87;

                  for (int[] var94 : var85) {
                     int var95 = (int)var73.getOffset() + var94[1] * 2;
                     var52.q(var34, var95, false);
                     if (this.nv != null) {
                        String var33 = this.nv.generateAutoCommentForPotentialResourceId(var89);
                        if (var33 != null) {
                           this.NX.space(2);
                           this.NX.appendCommentAuto("# " + var33);
                        }
                     }

                     this.NX.eol();
                     var89++;
                  }

                  this.NX.decrementIndentationLevel();
                  this.NX.q(bja.qa);
                  this.NX.eol();
               } else if (var78 == 44) {
                  this.NX.disablePadding();
                  IDalvikInstructionSwitchData var81 = var73.getSwitchData();
                  var52.q(var34, var81.getOffset(), true);
                  this.NX.eol();
                  this.q(var81.getOffset(), null);
                  this.NX.q(bja.Hk);
                  this.NX.eol();
                  this.NX.enablePadding();
                  this.NX.incrementIndentationLevel();
                  int var84 = 0;

                  for (int[] var29 : var81.getElements()) {
                     int var30 = var29[0];
                     int var31 = (int)var73.getOffset() + var29[1] * 2;
                     var84 = this.q(var30, var84, false);
                     this.NX.append(" -> ");
                     var52.q(var34, var31, false);
                     if (this.nv != null) {
                        String var32 = this.nv.generateAutoCommentForPotentialResourceId(var30);
                        if (var32 != null) {
                           this.NX.space(2);
                           this.NX.appendCommentAuto("# " + var32);
                        }
                     }

                     this.NX.eol();
                  }

                  this.NX.decrementIndentationLevel();
                  this.NX.q(bja.Me);
                  this.NX.eol();
               } else {
                  if (var78 != 38) {
                     throw new JebRuntimeException("Unknown pseudo-opcode: " + var78);
                  }

                  this.NX.disablePadding();
                  IDalvikInstructionArrayData var80 = var73.getArrayData();
                  var52.q(var34, var80.getOffset(), true);
                  this.NX.eol();
                  this.q(var80.getOffset(), null);
                  this.NX.q(bja.PV);
                  byte[][] var83 = var80.getElements();
                  if (var83.length == 0) {
                     this.NX.append(" 0");
                  } else {
                     this.NX.append(" " + Integer.toString(var83[0].length));
                  }

                  if (!this.Rv) {
                     this.NX.append(" x 0x" + Formatter.toHexString(var83.length, true));
                  }

                  this.NX.eol();
                  this.NX.enablePadding();
                  this.NX.incrementIndentationLevel();
                  long[] var26 = var80.asArrayOfLongs();

                  for (int var27 = 0; var27 < var26.length; var27++) {
                     long var28 = var26[var27];
                     if (var28 >= 0L) {
                        this.NX.append("0x" + Formatter.toHexString(var28, true));
                     } else {
                        this.NX.append("-0x" + Formatter.toHexString(-var28, true));
                     }

                     this.NX.eol();
                  }

                  this.NX.decrementIndentationLevel();
                  this.NX.q(bja.oQ);
                  this.NX.eol();
               }
            }

            this.NX.disablePadding();
            this.NX.unrecordLineCoordinates();

            for (Entry var74 : var44.entrySet()) {
               if ((Integer)var74.getKey() > var62) {
                  this.q(true, var3, (Integer)var74.getKey(), (byte[])var74.getValue(), var37, var8, var52, var34);
               }
            }

            if (this.Lj) {
               bjh.eo var69 = var52.q();
               if (!var69.xK.isEmpty()) {
                  Object[] var10000 = new Object[]{var3.getSignature(true)};

                  for (int var79 : var69.xK) {
                     var10000 = new Object[]{var79};
                  }
               }
            }
         }
      }
   }

   private void q(bip var1, bjh var2, Collection var3) {
      this.NX.enablePadding();

      for (biw var5 : var3) {
         int var6 = var5.getTryAddress();
         int var7 = var5.RF();

         for (biv var9 : var5.getHandlers()) {
            if (var9.isTyped()) {
               this.NX.q(bja.HF);
               this.NX.space();
               this.NX.Uv(this.Gu, var9.RF());
               this.NX.space();
            } else {
               this.NX.q(bja.LK);
               this.NX.space();
            }

            this.NX.append("{");
            var2.q(var1, var6, false);
            this.NX.append(" .. ");
            var2.q(var1, var7, false);
            this.NX.append("} ");
            var2.q(var1, var9.getAddress(), false);
            this.NX.eol();
         }
      }

      this.NX.disablePadding();
   }

   private int q(int var1) {
      return this.q(var1, 0, true);
   }

   private int q(int var1, int var2, boolean var3) {
      long var4 = var1 & 4294967295L;
      if (var2 == 0) {
         var2 = this.Gu.Uv(var4);
      }

      String var6 = null;
      if (var2 == 16) {
         var6 = "0x" + Formatter.toHexString(var1, true);
      } else if (var2 == 8) {
         var6 = "0" + Integer.toOctalString(var1);
      } else if (var2 == -1 && var1 >= 0 && var1 <= 65535) {
         var6 = "'" + Formatter.escapeString(Character.toString((char)var1)) + "'";
      }

      if (var6 == null) {
         var2 = 10;
         var6 = Integer.toString(var1);
      }

      this.NX.appendAndRecord(var6, ItemClassIdentifiers.NUMBER, this.Gu.q(Long.valueOf(var4)));
      return var2;
   }

   private void q(IDalvikInstruction var1, bjp var2, int var3, IDexDebugInfo var4, bjh var5, bip var6) {
      long var7 = var1.getOffset();
      int var9 = (int)var7;
      InstructionCoordinates var10 = new InstructionCoordinates(var2.getIndex(), var9);
      this.NX.recordLineCoordinates(var10);
      int var11 = var1.getParameterCount();
      if (var4 != null) {
         this.NX.enablePadding();
         IDexDebugLine var12 = var4.getLineInfo(var9 / 2);
         if (var12.getSourceIndex() >= 0) {
            this.NX.q(biz.q);
            this.NX.space();
            this.NX.q(this.Gu, var12.getSourceIndex());
            this.NX.eol();
         }

         if (var12.isPrologueEnd()) {
            this.NX.q(biz.RF);
            this.NX.eol();
         }

         if (var12.isEpilogueBegin()) {
            this.NX.q(biz.xK);
            this.NX.eol();
         }

         if (this.eJ && var12.getLineNumber() >= 0) {
            this.NX.q(biz.Dw);
            this.NX.append(" " + var12.getLineNumber());
            this.NX.eol();
         }

         for (IDexDebugVariable var14 : var12.getVariables()) {
            this.NX.q(biz.Uv);
            this.NX.append(" v" + var14.getRegister() + ", \"" + this.Gu.q(var14.getNameIndex(), "", true) + "\":" + this.Gu.q(var14.getTypeIndex(), false));
            this.NX.eol();
         }

         for (int var30 : var12.getVariablesEnd()) {
            this.NX.q(biz.oW);
            this.NX.append(" v" + var30);
            this.NX.eol();
         }

         for (int var31 : var12.getVariablesRestart()) {
            this.NX.q(biz.gO);
            this.NX.append(" v" + var31);
            this.NX.eol();
         }

         this.NX.disablePadding();
      }

      this.RF(var10);
      this.q(var9, var1.getCode());
      String var26 = var1.getMnemonic();
      int var29 = var1.getOpcode();
      this.NX.appendAndRecord(var26, ItemClassIdentifiers.MNEMONIC, var26.hashCode());
      if (var11 >= 1) {
         int var32 = Math.max(1, 20 - var26.length());
         this.NX.space(var32);
      }

      boolean var33 = false;
      byte var15 = 1;
      if (this.Rv
         && (
            var29 >= 110 && var29 <= 114
               || var29 >= 116 && var29 <= 120
               || var29 == 36
               || var29 == 37
               || var29 >= 8959 && var29 <= 9983
               || var29 == 1535
               || var29 == 250
               || var29 == 251
               || var29 == 252
               || var29 == 253
         )) {
         var33 = true;
         if (var29 == 250 || var29 == 251) {
            var15 = 2;
         }
      }

      StringBuilder var16 = null;

      for (int var17 = 0; var17 < var11; var17++) {
         int var18;
         long var19;
         if (var33) {
            var18 = var1.getParameterType((var17 + var15) % var11);
            var19 = var1.getParameterValue((var17 + var15) % var11);
            if (var11 == 1) {
               this.NX.append("{}, ");
            } else if (var17 == 0) {
               this.NX.append("{");
            } else if (var17 == var11 - var15) {
               this.NX.append("}");
            }
         } else {
            var18 = var1.getParameterType(var17);
            var19 = var1.getParameterValue(var17);
         }

         if (var17 >= 1) {
            this.NX.appendParameterSeparator();
         }

         if (var18 == 0) {
            String var21 = this.q((int)var19, var3);
            long var22 = 648518346341351424L | var19;
            this.NX.appendAndRecord(var21, ItemClassIdentifiers.IDENTIFIER, var22);
         } else if (var18 == 4) {
            String var34 = this.q((int)(var19 & 65535L), var3);
            String var39 = this.q((int)(var19 >> 32 & 65535L), var3);
            this.NX.appendAndRecord(var34, ItemClassIdentifiers.IDENTIFIER, var34.hashCode());
            this.NX.append(" .. ");
            this.NX.appendAndRecord(var39, ItemClassIdentifiers.IDENTIFIER, var39.hashCode());
         } else if (var18 == 1) {
            long var35 = var19;
            int var23 = this.Gu.Uv(var19);
            String var24 = null;
            if (var29 == 18) {
               if (var23 == 16 && var19 >= 10L) {
                  var24 = "0x" + Formatter.toHexString(var19, true);
               } else if (var23 == 8 && var19 >= 8L) {
                  var24 = "0" + Long.toOctalString(var19);
               } else if (var23 == -1 && var19 >= 0L && var19 <= 65535L) {
                  var24 = "'" + Formatter.escapeString(Character.toString((char)var19)) + "'";
               }
            } else if (var29 >= 216 && var29 <= 226) {
               if (var23 == 16) {
                  if (var19 >= 0L) {
                     var24 = "0x" + Formatter.toHexString(var19, true, 2);
                  } else if (var19 < 0L) {
                     var35 = -var19;
                     var24 = "-0x" + Formatter.toHexString(var35, true, 2);
                  }
               } else if (var23 == 8) {
                  if (var19 >= 0L) {
                     var24 = "0" + Long.toOctalString(var19);
                  } else if (var19 < 0L) {
                     var35 = -var19;
                     var24 = "-0" + Long.toOctalString(var35);
                  }
               }
            } else if (var29 != 19 && var29 != 22 && (var29 < 208 || var29 > 215)) {
               if (var29 != 20 && var29 != 21 && var29 != 23) {
                  if (var29 == 24 || var29 == 25) {
                     if (var23 == 16) {
                        var24 = "0x" + Formatter.toHexString(var19, true, 16) + "L";
                     } else if (var23 == 8) {
                        var24 = "0" + Long.toOctalString(var19) + "L";
                     } else if (var29 == 24 && var23 == -1 && var19 >= 0L && var19 <= 65535L) {
                        var24 = "'" + Formatter.escapeString(Character.toString((char)var19)) + "'";
                     } else {
                        var24 = Long.toString(var19) + "L";
                     }
                  }
               } else if (var23 == 16) {
                  var24 = "0x" + Formatter.toHexString((int)var19, true, 8);
                  var35 = var19 & 4294967295L;
               } else if (var23 == 8) {
                  var24 = "0" + Long.toOctalString((int)var19);
                  var35 = var19 & 4294967295L;
               } else if (var29 == 20 && var23 == -1 && var19 >= 0L && var19 <= 65535L) {
                  var24 = "'" + Formatter.escapeString(Character.toString((char)var19)) + "'";
               }
            } else if (var23 == 16) {
               if (var19 >= 0L) {
                  var24 = "0x" + Formatter.toHexString(var19, true, 4);
               } else if (var19 < 0L) {
                  var35 = -var19;
                  var24 = "-0x" + Formatter.toHexString(var35, true, 4);
               }
            } else if (var23 == 8) {
               if (var19 >= 0L) {
                  var24 = "0" + Long.toOctalString(var19);
               } else if (var19 < 0L) {
                  var35 = -var19;
                  var24 = "-0" + Long.toOctalString(var35);
               }
            } else if (var29 == 19 && var23 == -1 && var19 >= 0L && var19 <= 65535L) {
               var24 = "'" + Formatter.escapeString(Character.toString((char)var19)) + "'";
            }

            if (this.nv != null && (var35 & -4294967296L) == 0L) {
               String var25 = this.nv.generateAutoCommentForPotentialResourceId((int)var35);
               if (var25 != null) {
                  var16 = this.q(var16, var25);
               }
            }

            if (var24 == null) {
               var24 = Long.toString(var35);
            }

            this.NX.appendAndRecord(var24, ItemClassIdentifiers.NUMBER, this.Gu.q(Long.valueOf(var19)));
         } else if (var18 == 3) {
            int var36 = (int)var1.getOffset() + (int)var19 * 2;
            var5.q(var6, var36, false);
         } else if (var18 != 2 && var18 != 5) {
            JebRuntimeException var38 = new JebRuntimeException("Unknown parameter type: " + var18);
            JebCoreService.notifySilentExceptionToClient(var38);
         } else {
            int var37 = (int)var19;
            int var40 = var18 == 2 ? var1.getParameterFirstIndexType() : var1.getParameterSecondIndexType();
            switch (var40) {
               case 16:
                  this.NX.q(this.Gu, var37);
                  break;
               case 17:
                  this.NX.Uv(this.Gu, var37);
                  break;
               case 18:
                  this.NX.xK(this.Gu, var37);
                  break;
               case 19:
                  this.NX.RF(this.Gu, var37);
                  if (this.AB) {
                     int var42 = this.Gu.Rr().q(var2.getIndex(), var1.getOffset());
                     if (var42 >= 0 && var42 != var37) {
                        String var43 = Strings.ff("actual call site: %s", this.Gu.gO(var42).getSignature());
                        var16 = this.q(var16, var43);
                     }
                  }
                  break;
               case 20:
                  this.NX.Dw(this.Gu, var37);
                  break;
               case 21:
                  this.q(this.NX, var37);
                  break;
               case 22:
                  this.RF(this.NX, var37);
                  break;
               case 23:
               case 24:
               case 25:
               case 26:
               case 27:
               case 28:
               case 29:
               case 30:
               case 31:
               default:
                  JebRuntimeException var41 = new JebRuntimeException("Unknown index type for insn: " + var1);
                  JebCoreService.notifySilentExceptionToClient(var41);
                  break;
               case 32:
                  this.NX.append("inline@" + var37);
                  break;
               case 33:
                  this.NX.append("vtaboff@" + var37);
                  break;
               case 34:
                  this.NX.append("fieldoff@" + var37);
            }
         }
      }

      if (var1.isOptimized()) {
         var16 = this.q(var16, "optimized instruction");
      }

      this.q(var10, var16 == null ? null : var16.toString());
      this.NX.eol(var10);
      this.NX.unrecordLineCoordinates();
   }

   StringBuilder q(StringBuilder var1, String var2) {
      if (var1 == null) {
         var1 = new StringBuilder(var2);
      } else {
         var1.append("\n").append(var2);
      }

      return var1;
   }

   private void q(boolean var1, bjp var2, int var3, byte[] var4, int var5, IDexDebugInfo var6, bjh var7, bip var8) {
      int var9 = var4.length;
      if (var9 != 0) {
         boolean var10 = false;
         if (var9 <= 3) {
            var10 = true;

            for (int var11 = 0; var11 < var9; var11++) {
               if (var4[var11] != 0) {
                  var10 = false;
                  break;
               }
            }
         }

         if (!var10 || var1) {
            if (var10) {
               this.NX.appendCommentAuto("# " + S.L("(Padding)"));
            } else {
               this.NX.appendCommentAuto("# " + Strings.ff(S.L("(Gap) 0x%X bytes"), var9));
            }

            this.NX.eol();
            if (this.GY) {
               bii var16 = new bii(null, null, null, 0, true, true);
               var16.q(var4);
               int var12 = 0;

               while (var12 < var9) {
                  bie var13 = null;

                  try {
                     var13 = var16.q();
                  } catch (Exception var15) {
                     EB.catchingSilent(var15);
                  }

                  if (var13 == null) {
                     this.q(var12, Arrays.copyOfRange(var4, var12, var12 + 1));
                     this.NX.space(2);
                     this.NX.appendCommentAuto("# " + S.L("(Parsing failed)"));
                     this.NX.eol();
                     var16.q(1);
                     var12++;
                  } else {
                     var13.q(var3);
                     this.q(var13, var2, var5, var6, var7, var8);
                     var16.q(var13.getSize());
                     var12 += var13.getSize();
                  }
               }
            } else if (this.Bu) {
               this.q(var3, var4);
               this.NX.eol();
            }
         }
      }
   }

   private String q(int var1, int var2) {
      return this.YN && var1 >= var2 ? "p" + (var1 - var2) : "v" + var1;
   }

   private void q(int var1, byte[] var2) {
      this.RF(var1);
      this.q(var2);
   }

   private void RF(int var1) {
      if (this.Xo) {
         if (var1 < 0) {
            this.NX.space(10);
         } else {
            String var2 = Formatter.toHexString(var1, true, 8);
            this.NX.appendAndRecord(var2, ItemClassIdentifiers.ADDRESS);
            this.NX.space(2);
         }
      }
   }

   private void q(byte[] var1) {
      if (this.Bu) {
         if (var1 == null) {
            this.NX.space(24);
         } else {
            String var2 = Formatter.formatBinaryLine(var1, 0, var1.length, 8).toString();
            this.NX.appendAndRecord(var2, ItemClassIdentifiers.BYTECODE);
         }
      }
   }

   private void q(IDexAnnotationItem var1) {
      IDexAnnotation var2 = var1.getAnnotation();
      this.NX.q(bja.Dw);
      this.NX.space();
      this.xK(var1.getVisibility());
      this.q(var2);
      this.NX.q(bja.Uv);
      this.NX.eol();
   }

   private void q(IDexAnnotation var1) {
      this.NX.Uv(this.Gu, var1.getTypeIndex());
      this.NX.eol();
      this.NX.incrementIndentationLevel();

      for (IDexAnnotationElement var3 : var1.getElements()) {
         String var4 = this.Gu.q(var3.getNameIndex(), "", true);
         IDexValue var5 = var3.getValue();
         this.NX.append(var4);
         this.NX.append(" = ");
         this.q(var5);
         this.NX.eol();
      }

      this.NX.decrementIndentationLevel();
   }

   private void xK(int var1) {
      if (var1 == 0) {
         this.NX.append("build ");
      } else if (var1 == 1) {
         this.NX.append("runtime ");
      } else if (var1 == 2) {
         this.NX.append("system ");
      } else {
         this.NX.append("unknown-visibility ");
      }
   }

   private void RF(IDexAnnotation var1) {
      this.NX.q(bja.oW);
      this.NX.space();
      this.q(var1);
      this.NX.q(bja.gO);
      this.NX.eol();
   }

   private void q(IDexValue var1) {
      switch (var1.getType()) {
         case 0:
            this.NX.append("0x" + Formatter.toHexString(var1.getByte(), true));
            break;
         case 1:
         case 5:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 18:
         case 19:
         case 20:
         default:
            this.NX.append("<unsupported_value_" + var1.getType() + ">");
            break;
         case 2:
            this.NX.append("0x" + Formatter.toHexString(var1.getShort(), true));
            break;
         case 3:
            this.NX.append("'" + Formatter.escapeCharacter(var1.getChar(), !this.Rv) + "'");
            break;
         case 4:
            this.NX.append("0x" + Formatter.toHexString(var1.getInt(), true));
            break;
         case 6:
            this.NX.append("0x" + Formatter.toHexString(var1.getLong(), true) + "L");
            break;
         case 16:
            this.NX.append(Strings.ff("%ff", var1.getFloat()));
            break;
         case 17:
            this.NX.append(Strings.ff("%f", var1.getDouble()));
            break;
         case 21:
            this.NX.q(this.Gu, this.Gu.Uv(var1.getMethodTypeIndex()));
            break;
         case 22:
            this.RF(this.NX, var1.getMethodHandleIndex());
            break;
         case 23:
            this.NX.q(this.Gu, var1.getStringIndex());
            break;
         case 24:
            this.NX.Uv(this.Gu, var1.getTypeIndex());
            break;
         case 25:
            this.NX.xK(this.Gu, var1.getFieldIndex());
            break;
         case 26:
            this.NX.RF(this.Gu, var1.getMethodIndex());
            break;
         case 27:
            this.NX.xK(this.Gu, var1.getEnumIndex());
            break;
         case 28:
            this.NX.append("{");
            this.NX.eol();
            this.NX.incrementIndentationLevel();
            int var2 = 0;
            List var3 = var1.getArray();

            for (IDexValue var5 : var3) {
               this.q(var5);
               if (++var2 < var3.size()) {
                  this.NX.append(",");
               }

               this.NX.eol();
            }

            this.NX.decrementIndentationLevel();
            this.NX.append("}");
            break;
         case 29:
            this.RF(var1.getAnnotation());
            break;
         case 30:
            this.NX.append("null");
            break;
         case 31:
            this.NX.append(Boolean.toString(var1.getBoolean()));
      }
   }

   private void q(com.pnfsoftware.jeb.corei.parsers.dex.CU var1) {
      if (this.CY && var1.isRenamed()) {
         this.NX.appendCommentAuto("# " + Strings.ff(S.L("original signature: %s"), var1.getSignature(false)));
         this.NX.eol();
      }
   }

   public static void q(bje var0, int var1, int var2, int var3) {
      int[] var4 = new int[]{0};
      q(var0, var4, var2, 1, bjf.Bu);
      q(var0, var4, var2, 2, bjf.EB);
      q(var0, var4, var2, 4, bjf.Xo);
      q(var0, var4, var2, 8, bjf.eJ);
      q(var0, var4, var2, 16, bjf.xW);
      q(var0, var4, var2, 4096, bjf.Ri);
      if (var1 == 0) {
         q(var0, var4, var2, 1024, bjf.q);
         q(var0, var4, var2, 512, bjf.mI);
         q(var0, var4, var2, 8192, bjf.RF);
         q(var0, var4, var2, 16384, bjf.PV);
      } else if (var1 == 1) {
         q(var0, var4, var2, 64, bjf.Gu);
         q(var0, var4, var2, 128, bjf.CY);
         q(var0, var4, var2, 16384, bjf.PV);
      } else if (var1 == 2) {
         q(var0, var4, var2, 32, bjf.ZT);
         q(var0, var4, var2, 64, bjf.oW);
         q(var0, var4, var2, 128, bjf.Tq);
         q(var0, var4, var2, 256, bjf.ui);
         q(var0, var4, var2, 1024, bjf.q);
         q(var0, var4, var2, 2048, bjf.YN);
         q(var0, var4, var2, 65536, bjf.JY);
         q(var0, var4, var2, 131072, bjf.LK);
      }

      if (var3 == 1 || var3 == -1 && var4[0] > 0) {
         var0.space();
      }
   }

   private static void q(bje var0, int[] var1, int var2, int var3, bjf var4) {
      if (var4 != null && (var2 & var3) != 0) {
         if (var1[0] > 0) {
            var0.space();
         }

         var0.q(var4);
         var1[0]++;
      }
   }

   void q(bje var1, int var2) {
      bjm var3 = this.Gu.gP(var2);
      if (var3 == null) {
         var1.append(Strings.ff("INVALID_CALLSITE_%d", var2));
      } else {
         this.q(var1, var3);
      }
   }

   void q(bje var1, bjm var2) {
      int var3 = var2.getLinkerMethodHandleIndex();
      bjs var4 = var2.xK();
      bjr var5 = var2.Dw();
      var1.append("call_site_" + var2.getIndex() + "(");
      var1.q(this.Gu, var4.getIndex());
      var1.appendParameterSeparator();
      var1.Dw(this.Gu, var5.getIndex());

      for (int var6 = 3; var6 < var2.getCallSiteValues().size(); var6++) {
         var1.appendParameterSeparator();
         bia var7 = var2.q(var6);
         this.q(var7);
      }

      var1.append(")");
      var1.append("@");
      this.q(var1, var3, false);
   }

   void RF(bje var1, int var2) {
      this.q(var1, var2, true);
   }

   void q(bje var1, int var2, boolean var3) {
      bjq var4 = this.Gu.za(var2);
      if (var4 == null) {
         var1.append(Strings.ff("INVALID_METHOD_HANDLE_%d", var2));
      } else {
         this.q(var1, var4, var3);
      }
   }

   void q(bje var1, bjq var2, boolean var3) {
      DexMethodHandleType var4 = var2.getMethodHandleType();
      if (var3) {
         var1.append(var4.toString());
         var1.append("@");
      }

      if (var4.isMethodInvoker()) {
         var1.RF(this.Gu, var2.getFieldOrMethodIndex());
      } else if (var4.isFieldAccessor()) {
         var1.xK(this.Gu, var2.getFieldOrMethodIndex());
      } else {
         var1.append(var2.generate(true));
      }
   }

   @Override
   public IDexUnit getUnit() {
      return this.Gu;
   }

   @Override
   public IDexItemToAnchor getCodeItemToAnchor() {
      return this.br;
   }

   @Override
   public boolean hasBinaryRepresentation() {
      return true;
   }

   @Override
   public IBinaryRepresentation getBinaryRepresentation() {
      if (this.pQ == null) {
         synchronized (this) {
            this.pQ = new bjb.eo();
         }
      }

      return this.pQ;
   }

   private class eo implements IBinaryRepresentation {
      Map q = new HashMap();
      TreeMap RF = new TreeMap();
      byte[] xK;

      eo() {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         int var3 = 0;

         for (bjp var5 : bjb.this.Gu.getMethods()) {
            if (var5.isInternal() && var5.RF().q() != null) {
               bip var6 = var5.RF().q();
               byte[] var7 = bjb.this.Gu.getDexEntry(var6.getDexEntryIndex()).getData();
               int var8 = var6.getInstructionsOffset();
               int var9 = var6.getInstructionsSize();
               var2.write(var7, var8, var9);
               this.RF.put(var3, var5.getIndex());
               this.q.put(var5.getIndex(), var3);
               var3 += var9;
            }
         }

         this.xK = var2.toByteArray();
      }

      @Override
      public long getBaseOffsetHint() {
         return 0L;
      }

      @Override
      public int read(long var1, int var3, byte[] var4, int var5) {
         if (var1 >= 0L && var1 < this.xK.length) {
            int var6 = (int)var1 + var3;
            if (var6 > this.xK.length) {
               var3 = this.xK.length - (int)var1;
            }

            System.arraycopy(this.xK, (int)var1, var4, var5, var3);
            return var3;
         } else {
            return 0;
         }
      }

      @Override
      public long find(long var1, long var3, byte[] var5, byte[] var6) {
         if (Longs.compareUnsigned(var1, var3) <= 0) {
            if (var3 == -1L) {
               var3 = this.xK.length;
            }

            return Bytes.indexOf(this.xK, (int)var1, (int)var3, var5, 0, var5.length, var6);
         } else {
            if (var1 == -1L) {
               var1 = this.xK.length;
            }

            return Bytes.lastIndexOf(this.xK, (int)var1, var5, var6);
         }
      }

      @Override
      public ICoordinates convertOffsetToCoordinates(long var1) {
         Integer var3 = (Integer)this.RF.floorKey((int)var1);
         if (var3 == null) {
            return null;
         } else {
            int var4 = (int)var1 - var3;
            int var5 = (Integer)this.RF.get(var3);
            InstructionCoordinates var6 = new InstructionCoordinates(var5, var4);
            String var7 = bjb.this.Gu.xK().q(var6);
            return bjb.this.addressToCoordinates(var7);
         }
      }

      @Override
      public long convertCoordinatesToOffset(ICoordinates var1) {
         String var2 = bjb.this.coordinatesToAddress(var1);
         if (var2 == null) {
            return -1L;
         } else {
            ICodeCoordinates var3 = bjb.this.Gu.xK().q(var2);
            Integer var4 = CodeCoordinatesUtil.getMethodObjectIndex(var3);
            if (var4 == null) {
               return -1L;
            } else {
               Integer var5 = (Integer)this.q.get(var4);
               if (var5 == null) {
                  return -1L;
               } else {
                  if (var3 instanceof InstructionCoordinates) {
                     var5 = var5 + ((InstructionCoordinates)var3).getOffset();
                  }

                  return var5.intValue();
               }
            }
         }
      }
   }
}
