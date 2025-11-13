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

public class bfg extends CodeDocument implements IDexDisassemblyDocument {
   private static final ILogger UT = GlobalLog.getLogger(bfg.class);
   private boolean E;
   private boolean sY;
   private boolean ys;
   private boolean ld;
   private boolean gp;
   private boolean oT;
   private boolean fI;
   private int WR;
   private int NS;
   private boolean vP;
   private boolean xC;
   private boolean ED;
   private boolean Sc;
   private boolean ah;
   private DexDisassemblyProperties eP;
   private String UO = "";
   private boolean Ab;
   private com.pnfsoftware.jeb.corei.parsers.dex.vi rl;
   private IPropertyManager z;
   private IEventListener Ek;
   private IEventListener hK;
   private bfj Er;
   private bfd FE;
   private ConcurrentHashMap Aj;
   private String EX;
   private String LM;
   private boolean mv = false;
   private ApkStringResHelper sO;
   int pC;
   int A;
   int kS;
   int wS;
   private Map os = new HashMap();
   private List Cu = new ArrayList();
   private Map hZ = new HashMap();
   private transient bfg.Av UW;

   public bfg(com.pnfsoftware.jeb.corei.parsers.dex.vi var1) {
      if (var1 == null) {
         throw new JebRuntimeException("Cannot generate the disassembly document of a null DEX unit");
      } else if (!var1.isProcessed()) {
         throw new JebRuntimeException("The DEX unit must be processed before generating the disassembly document");
      } else {
         this.rl = var1;
         this.FE = new bfd();
         this.Aj = new ConcurrentHashMap();
         this.pC();
         this.pC(false);
         var1.addListener(this.Ek = new bfh(this));
         this.z = var1.getPropertyManager();
         this.z.addListener(this.hK = new bfi(this));
         if (var1.getParent() instanceof com.pnfsoftware.jeb.corei.parsers.apk.Ws) {
            com.pnfsoftware.jeb.corei.parsers.apk.Ws var2 = (com.pnfsoftware.jeb.corei.parsers.apk.Ws)var1.getParent();
            this.sO = var2.pC();
         }
      }
   }

   private void pC() {
      List var1 = this.rl.getClasses();
      this.FE.pC();
      long var2 = 0L;
      this.FE.pC(var2++);

      for (bfs var5 : var1) {
         this.FE.pC(var2++, var5);
         List var6 = var5.getFields();
         if (var6 != null) {
            for (bft var8 : var6) {
               this.FE.pC(var2++, var8);
            }
         }

         List var14 = var5.getMethods();
         if (var14 != null) {
            for (bfu var9 : var5.getMethods()) {
               bev var10 = var9.A().pC();
               if (var10 == null) {
                  this.FE.pC(var2++, var9);
               } else if (var10.getControlFlowGraph() != null) {
                  for (BasicBlock var12 : var10.getControlFlowGraph()) {
                     this.FE.pC(var2++, var9, var12);
                  }
               }
            }
         }
      }
   }

   public static void pC(IPropertyDefinitionManager var0) {
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

   private void pC(boolean var1) {
      IPropertyManager var2 = this.rl.getPropertyManager();
      if (this.eP != null && this.eP.getShowAddresses() != null) {
         this.E = this.eP.getShowAddresses();
      } else {
         this.E = var2.getBoolean("ShowAddresses", true);
      }

      if (this.eP != null && this.eP.getShowBytecode() != null) {
         this.sY = this.eP.getShowBytecode();
      } else {
         this.sY = var2.getBoolean("ShowBytecode", false);
      }

      if (this.eP != null && this.eP.getShowAnnotations() != null) {
         this.ys = this.eP.getShowAnnotations();
      } else {
         this.ys = var2.getBoolean("ShowAnnotations", true);
      }

      if (this.eP != null && this.eP.getShowDebugDirectives() != null) {
         this.ld = this.eP.getShowDebugDirectives();
      } else {
         this.ld = var2.getBoolean("ShowDebugDirectives", false);
      }

      if (this.eP != null && this.eP.getShowLineNumbers() != null) {
         this.gp = this.eP.getShowLineNumbers();
      } else {
         this.gp = var2.getBoolean("ShowLineNumbers", false);
      }

      if (this.eP != null && this.eP.getUsePForParameters() != null) {
         this.oT = this.eP.getUsePForParameters();
      } else {
         this.oT = var2.getBoolean("UsePForParameters", true);
      }

      if (this.eP != null && this.eP.getSmaliCompatibility() != null) {
         this.fI = this.eP.getSmaliCompatibility();
      } else {
         this.fI = var2.getBoolean("SmaliCompatibility", false);
      }

      if (this.eP != null && this.eP.getClassSeparatorLength() != null) {
         this.WR = this.eP.getClassSeparatorLength();
      } else {
         this.WR = var2.getInteger("ClassSeparatorLength", 0);
      }

      this.EX = this.WR <= 0 ? null : Strings.generate('=', this.WR);
      if (this.eP != null && this.eP.getMethodSeparatorLength() != null) {
         this.NS = this.eP.getMethodSeparatorLength();
      } else {
         this.NS = var2.getInteger("MethodSeparatorLength", 0);
      }

      this.LM = this.NS <= 0 ? null : Strings.generate('-', this.NS);
      if (this.eP != null && this.eP.getShowSpaceBetweenBlocks() != null) {
         this.vP = this.eP.getShowSpaceBetweenBlocks();
      } else {
         this.vP = var2.getBoolean("ShowSpaceBetweenBlocks", false);
      }

      if (this.eP != null && this.eP.getShowInstructionsInGaps() != null) {
         this.xC = this.eP.getShowInstructionsInGaps();
      } else {
         this.xC = var2.getBoolean("ShowInstructionsInGaps", false);
      }

      if (this.eP != null && this.eP.getGenerateCatchDirectivesAtMethodEnd() != null) {
         this.ED = this.eP.getGenerateCatchDirectivesAtMethodEnd();
      } else {
         this.ED = var2.getBoolean("GenerateCatchDirectivesAtMethodEnd", false);
      }

      if (this.eP != null && this.eP.getShowActualCallsites() != null) {
         this.Sc = this.eP.getShowActualCallsites();
      } else {
         this.Sc = var2.getBoolean("ShowActualCallsites", true);
      }

      if (this.eP != null && this.eP.getShowOriginalNames() != null) {
         this.ah = this.eP.getShowOriginalNames();
      } else {
         this.ah = var2.getBoolean("ShowOriginalNames", false);
      }

      this.A();
      this.UO = Strings.spaces(this.kS);
      if (this.Er != null) {
         this.Er.setPaddingString(this.UO);
      }

      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   private void A() {
      this.pC = 0;
      this.A = this.pC;
      if (this.E) {
         this.A += 10;
      }

      this.kS = this.A;
      if (this.sY) {
         this.kS += 24;
      }

      this.wS = this.kS + 40;
   }

   @Override
   public DexDisassemblyProperties getPropertyOverrides() {
      return this.eP;
   }

   @Override
   public void setPropertyOverrides(DexDisassemblyProperties var1) {
      this.eP = var1;
      this.pC(true);
   }

   @Override
   public void dispose() {
      if (!this.Ab) {
         super.dispose();
         this.Aj.clear();
         this.rl.removeListener(this.Ek);
         this.z.removeListener(this.hK);
         this.Ab = true;
      }
   }

   @Override
   public long getAnchorCount() {
      return this.FE.size();
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      int var3 = (int)var1.getAnchorId();
      if (var3 < 0 || var3 >= this.getAnchorCount()) {
         return null;
      } else if (var3 == 0) {
         return "#start#";
      } else {
         bfj var4 = (bfj)this.Aj.get(var3);
         if (var4 == null) {
            var4 = this.pC((long)var3, 1);
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
                  bfs var20 = this.rl.ys(var17);
                  return var20.getSignature(true);
               } else if (var8 instanceof FieldCoordinates) {
                  int var16 = ((FieldCoordinates)var8).getFieldId();
                  bft var19 = this.rl.E(var16);
                  return var19.getSignature(true);
               } else if (var8 instanceof MethodCoordinates) {
                  int var15 = ((MethodCoordinates)var8).getMethodId();
                  bfu var18 = this.rl.sY(var15);
                  return ((MethodCoordinates)var8).isEndFlag() ? var18.getSignature(true) + "+END" : var18.getSignature(true);
               } else if (var8 instanceof InstructionCoordinates) {
                  int var9 = ((InstructionCoordinates)var8).getMethodId();
                  bfu var10 = this.rl.sY(var9);
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

   public Coordinates pC(String var1, CoordinatesConversionPrecision var2) {
      Coordinates var3 = this.A(var1, var2);
      if (var3 == null && var1 != null && Strings.indexOf2(var1, '/', ';') == -1) {
         if (var1.indexOf(46) >= 0) {
            var1 = "L" + var1.replace('.', '/') + ";";
            var3 = this.A(var1, var2);
         } else {
            bfs var4 = this.rl.WR().A(var1);
            if (var4 != null) {
               var3 = this.A(var4.getSignature(), var2);
            }
         }
      }

      return var3 == null && "#start#".equals(var1) ? new Coordinates(0L) : var3;
   }

   public Coordinates A(String var1, CoordinatesConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         if (var2 == null) {
            var2 = CoordinatesConversionPrecision.BEST;
         }

         if (!var1.contains("->")) {
            bfs var21 = this.rl.WR().pC(var1);
            if (var21 == null) {
               return null;
            } else {
               Long var23 = this.FE.fromClass(var21);
               return var23 == null ? null : new Coordinates(var23, 0, 0);
            }
         } else if (var1.contains(":")) {
            bft var20 = this.rl.oT().pC(var1);
            if (var20 != null && var20.isInternal()) {
               Long var22 = this.FE.fromField(var20);
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

            bfu var24 = this.rl.fI().pC(var1);
            if (var24 != null && var24.isInternal()) {
               if (var3 <= 0) {
                  Long var7 = this.FE.fromMethod(var24);
                  if (var7 != null) {
                     return new Coordinates(var7, 0, 0);
                  }

                  var3 = 0;
               }

               bev var25 = var24.A().pC();
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
                     Long var26 = this.FE.fromInstruction(var24, var8);
                     if (var26 == null) {
                        return null;
                     } else {
                        bfj var11 = this.pC(var26, 1);
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
      Long var2 = this.FE.from(var1);
      return var2 == null ? null : this.pC(var2, 1, 0);
   }

   public bfj pC(long var1, int var3, int var4) {
      int var5 = (int)var1;
      if (var5 >= 0 && var5 < this.FE.size()) {
         synchronized (this) {
            this.Er = new bfj(var5, this.UO, this.fI);
            if (var3 > 0) {
               for (int var7 = var5; var7 < this.FE.size() && this.Er.getLineCount() < var3; var7++) {
                  this.pC((long)var7);
               }
            }

            if (var4 > 0) {
               int var12 = this.Er.getLineCount() + var4;
               bfj var8 = this.Er;

               for (int var9 = var5 - 1; var9 >= 0 && var8.getLineCount() < var12; var9--) {
                  this.Er = new bfj(var9, this.UO, this.fI);
                  this.pC((long)var9);
                  var8.prependCodePart(this.Er);
               }

               this.Er = var8;
            }

            this.Aj.clear();

            for (IAnchor var14 : this.Er.getAnchors()) {
               this.Aj.put((int)var14.getIdentifier(), this.Er);
            }

            return this.Er;
         }
      } else {
         return null;
      }
   }

   public bfj pC(long var1, int var3) {
      return this.pC(var1, var3, 0);
   }

   private void pC(long var1) {
      ICodeCoordinates var3 = this.FE.get(var1);
      if (var1 == 0L) {
         try {
            this.Er.registerAnchor("header");
            bfl var4 = new bfl(this.rl, this.Er);
            var4.pC(false);
            var4.pC();
         } catch (Exception var7) {
            JebCoreService.notifySilentExceptionToClient(var7);
         }

         this.Er.eol();
      } else if (var3 instanceof ClassCoordinates) {
         bfs var8 = this.rl.ys(((ClassCoordinates)var3).getClassId());
         this.pC(var8);
      } else if (var3 instanceof FieldCoordinates) {
         bft var9 = this.rl.E(((FieldCoordinates)var3).getFieldId());
         this.pC(var9);
      } else if (var3 instanceof MethodCoordinates) {
         bfu var10 = this.rl.sY(((MethodCoordinates)var3).getMethodId());
         this.pC(var10);
      } else {
         if (!(var3 instanceof InstructionCoordinates)) {
            throw new JebRuntimeException(
               Strings.ff("Unsupported anchor: id=%d, object=%s (%s)", var1, var3, var3 == null ? "null" : var3.getClass().getName())
            );
         }

         bfu var11 = this.rl.sY(((InstructionCoordinates)var3).getMethodId());
         int var5 = ((InstructionCoordinates)var3).getOffset();
         BasicBlock var6 = var11.A().pC().getControlFlowGraph().getBlockAt((long)var5);
         this.pC(var11, var6);
      }
   }

   private boolean pC(ICodeCoordinates var1) {
      return this.pC(var1, null);
   }

   private boolean pC(ICodeCoordinates var1, String var2) {
      Comment var3 = this.rl.getCommentManager().getComment2(var1);
      CommentGenerator var4 = new CommentGenerator(this.Er, "#");
      var4.setMargin(this.wS);
      return var4.genInline(var1, var3, 0, 2, var2);
   }

   private boolean A(ICodeCoordinates var1) {
      Comment var2 = this.rl.getCommentManager().getComment2(var1);
      if (var2 != null && var2.getPre() != null) {
         if (var1 instanceof InstructionCoordinates) {
            this.pC(-1, null);
         }

         CommentGenerator var3 = new CommentGenerator(this.Er, "#");
         return var3.genPre(var1, var2, null);
      } else {
         return false;
      }
   }

   private void pC(bfs var1) {
      this.Er.registerAnchor(var1.getSignature(false));
      ClassCoordinates var2 = new ClassCoordinates(var1.getIndex());
      this.Er.recordLineCoordinates(var2);
      if (this.EX != null) {
         this.Er.appendCommentAuto(this.EX);
         this.Er.eol();
      }

      this.A(var2);
      this.Er.pC(bff.pC);
      this.Er.space();
      pC(this.Er, 0, var1.getAccessFlags(), -1);
      this.Er.pC(this.rl, var1.getClassTypeIndex(), true);
      this.pC(var2);
      this.Er.eol(var2);
      this.pC((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var1);
      this.Er.pC(bff.A);
      this.Er.space();
      int var3 = var1.getSuperTypeIndex();
      if (var3 >= 0) {
         this.Er.UT(this.rl, var3);
      } else {
         String var4 = var1.getSupertypeSignature(true);
         this.Er.appendAndRecord(var4, ItemClassIdentifiers.CLASS_NAME);
      }

      this.Er.eol();
      if (this.ld) {
         this.Er.pC(bfe.pC);
         this.Er.space();
         int var9 = var1.getSourceStringIndex();
         if (var9 != -1) {
            this.Er.pC(this.rl, var9);
         }

         this.Er.eol();
      }

      this.Er.eol();
      int[] var10 = var1.getInterfaceTypeIndexes();
      if (var10.length > 0) {
         for (int var8 : var10) {
            this.Er.pC(bff.kS);
            this.Er.space();
            this.Er.UT(this.rl, var8);
            this.Er.eol();
         }

         this.Er.eol();
      }

      if (this.ys) {
         List var11 = var1.ys();
         if (!var11.isEmpty()) {
            for (IDexAnnotationItem var13 : var11) {
               this.pC(var13);
               this.Er.eol();
            }
         }
      }

      this.Er.unrecordLineCoordinates();
   }

   private void pC(bft var1) {
      bfy var2 = var1.wS();
      if (var2.isInternal()) {
         this.Er.registerAnchor(var1.getSignature(false));
         FieldCoordinates var3 = new FieldCoordinates(var1.getIndex());
         this.Er.recordLineCoordinates(var3);
         bfz var4 = var1.A();
         int var5 = 0;
         bfs var6 = var2.A();
         beg[] var7 = var6.E();

         for (bfz var9 : var6.sY().pC()) {
            if (var9 == var4) {
               break;
            }

            if (var9.isStatic()) {
               var5++;
            }
         }

         this.A(var3);
         this.Er.pC(bff.ys);
         this.Er.space();
         pC(this.Er, 1, var4.getAccessFlags(), -1);
         this.Er.appendAndRecord(var1.getName(true), ItemClassIdentifiers.FIELD_NAME, this.rl.pC(var1), 1);
         this.Er.append(":");
         this.Er.UT(this.rl, var1.getFieldTypeIndex());
         if (var4.isStatic() && var5 < var7.length) {
            this.Er.append(" = ");
            this.pC(var7[var5]);
         }

         this.pC(var3);
         this.Er.eol(var3);
         this.pC((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var1);
         if (this.ys) {
            List var11 = var6.pC(var4.getFieldIndex());
            if (var11 != null && !var11.isEmpty()) {
               this.Er.incrementIndentationLevel();

               for (IDexAnnotationItem var10 : var11) {
                  this.pC(var10);
               }

               this.Er.decrementIndentationLevel();
               this.Er.pC(bff.ld);
               this.Er.eol();
            }
         }

         this.Er.eol();
         this.Er.unrecordLineCoordinates();
      }
   }

   private void pC(bfu var1) {
      this.pC(var1, null);
   }

   private void pC(bfu var1, BasicBlock var2) {
      bfy var3 = var1.wS();
      if (var3.isInternal()) {
         bgd var4 = var1.A();
         bfs var5 = var3.A();
         boolean var6;
         if (var2 == null) {
            this.Er.registerAnchor(var1.getSignature(false));
            var6 = true;
         } else if (var2.getFirstAddress() == 0L) {
            this.Er.registerAnchor(var1.getSignature(false) + "+0h");
            var6 = true;
         } else {
            var6 = false;
         }

         boolean var7;
         if (var2 != null && (var4.pC() == null || var4.pC().getControlFlowGraph() == null || var4.pC().getControlFlowGraph().getLast() != var2)) {
            var7 = false;
         } else {
            var7 = true;
         }

         MethodCoordinates var8 = new MethodCoordinates(var1.getIndex());
         this.Er.recordLineCoordinates(var8);
         if (var6) {
            if (this.LM != null) {
               this.Er.appendCommentAuto(this.LM);
               this.Er.eol();
            }

            this.A(var8);
            this.Er.pC(bff.gp);
            this.Er.space();
            pC(this.Er, 2, var4.getAccessFlags(), -1);
            this.Er.appendAndRecord(var1.getName(true), ItemClassIdentifiers.METHOD_NAME, this.rl.pC(var1), 1);
            this.Er.pC(this.rl, var1.UT());
            this.pC(var8);
            this.Er.eol(var8);
            this.pC((com.pnfsoftware.jeb.corei.parsers.dex.Sv)var1);
         }

         this.Er.enablePadding();
         this.pC(var5, var4, var1, var2, var6, var7);
         this.Er.disablePadding();
         if (var7) {
            this.Er.recordLineCoordinates(new MethodCoordinates(var1.getIndex(), true));
            this.Er.pC(bff.oT);
            MethodCoordinates var9 = new MethodCoordinates(var1.getIndex(), true);
            this.pC(var9);
            this.Er.eol(var9);
            this.Er.eol();
            this.Er.unrecordLineCoordinates();
         }

         this.Er.unrecordLineCoordinates();
      }
   }

   ParametersInfo pC(bgd var1) {
      int var2 = var1.getMethodIndex();
      ParametersInfo var3 = (ParametersInfo)this.os.get(var2);
      if (var3 == null) {
         var3 = DexUtil.getMethodParametersInfo(this.rl, var1);
         int var4 = this.Cu.indexOf(var3);
         if (var4 >= 0) {
            this.os.put(var2, (ParametersInfo)this.Cu.get(var4));
         } else {
            this.os.put(var2, var3);
            this.Cu.add(var3);
         }
      }

      return var3;
   }

   public List A(bgd var1) {
      int var2 = var1.getMethodIndex();
      Object var3 = (List)this.hZ.get(var2);
      if (var3 == null) {
         HashSet var4 = new HashSet();
         var3 = new ArrayList();
         if (var1.pC() != null) {
            for (IDalvikInstruction var6 : var1.pC().getInstructions()) {
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

         this.hZ.put(var2, var3);
      }

      return (List)var3;
   }

   private void pC(bfs var1, bgd var2, bfu var3, BasicBlock var4, boolean var5, boolean var6) {
      if (var4 != null && !var5) {
         StringBuilder var7 = new StringBuilder(var3.getSignature(false)).append('+').append(Formatter.toHexString(var4.getFirstAddress(), true)).append('h');
         this.Er.registerAnchor(var7.toString());
      }

      bev var34 = var2.pC();
      IDexDebugInfo var8 = var34 != null && this.ld ? var34.getDebugInfo() : null;
      ParametersInfo var9 = this.pC(var2);
      if (var5) {
         if (var34 != null) {
            this.Er.pC(bff.Ab);
            this.Er.append(" " + var34.getRegisterCount());
            this.Er.eol();
            if (var34.hasParsingErrors()) {
               this.Er.appendCommentAuto("# " + S.L("Bytecode parsing errors!"));
               this.Er.eol();

               for (DalvikParserError var11 : var34.getParsingErrors()) {
                  this.Er.appendCommentAuto("# - " + var11);
                  this.Er.eol();
               }
            }
         }

         if (this.ys) {
            List var35 = var1.A(var2.getMethodIndex());
            if (var35 != null && !var35.isEmpty()) {
               for (IDexAnnotationItem var12 : var35) {
                  this.pC(var12);
               }
            }
         }

         boolean var36 = false;
         if (this.ys) {
            List var39 = var1.kS(var2.getMethodIndex());
            if (var39 != null && !var39.isEmpty()) {
               int[] var42 = null;
               if (var8 != null) {
                  var42 = var8.getParameterNameIndexes();
                  var36 = true;
               }

               int var13 = 0;

               for (List var15 : var39) {
                  this.Er.pC(bff.fI);
                  this.Er.append(" p" + (var13 < var9.getParameterCount() ? var9.getParameterIndex(var13) : -1));
                  if (var42 != null && var13 < var42.length && var42[var13] != -1) {
                     this.Er.appendParameterSeparator();
                     this.Er.pC(this.rl, var42[var13]);
                  }

                  this.Er.eol();
                  if (var15 != null && !var15.isEmpty()) {
                     this.Er.incrementIndentationLevel();

                     for (IDexAnnotationItem var17 : var15) {
                        this.pC(var17);
                     }

                     this.Er.decrementIndentationLevel();
                  }

                  this.Er.pC(bff.WR);
                  this.Er.eol();
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
               this.Er.pC(bff.fI);
               this.Er.append(" p" + (var40 < var9.getParameterCount() ? var9.getParameterIndex(var40) : -1));
               if (var50 != -1) {
                  this.Er.appendParameterSeparator();
                  this.Er.pC(this.rl, var50);
               }

               this.Er.eol();
               var40++;
            }
         }
      }

      int var37 = var34.getRegisterCount() - var9.getRegisterCount();
      CFG var41 = var34.getControlFlowGraph();
      if (var41 != null) {
         Map var44 = var34.getGaps();
         this.Er.disablePadding();
         boolean var46 = false;

         for (bfb var55 : var34.wS()) {
            if (var55.getTryAddress() == 0) {
               var46 = true;
               break;
            }
         }

         bfn var49 = new bfn(var34);
         bfm var52 = new bfm(var34, this.rl, this.Er);
         var52.pC(var49);
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
         this.Er.recordLineCoordinates(var57);
         if (this.vP && var56 > 0) {
            this.Er.eol();
         }

         if (var54.allinsize() >= 1 || var56 == 0 && var46) {
            int var60 = (int)((IDalvikInstruction)var54.get(0)).getOffset();
            var52.pC(var34, var60, true);
            bfn.Av var20 = var49.pC(var60);
            if (var20 != null && !var20.wS.isEmpty()) {
               TreeSet var21 = new TreeSet();

               for (bfa var23 : var20.wS) {
                  if (!var23.isTyped()) {
                     var21.add("*");
                  } else {
                     var21.add(this.rl.wS(var23.A()).getSignature());
                  }
               }

               if (!var21.isEmpty()) {
                  String var70 = Strings.ff(S.L("used for: %s"), Strings.join(", ", var21));
                  CommentGenerator var76 = new CommentGenerator(this.Er, "#");
                  var76.genInline(var57, var70, true);
               }
            }

            this.Er.eol();
         }

         this.Er.unrecordLineCoordinates();

         for (IDalvikInstruction var63 : var54) {
            this.pC(var63, var3, var37, var8, var52, var34);
         }

         int var62 = (int)var54.getEndAddress();
         byte[] var64 = (byte[])var44.get(var62);
         if (var64 != null) {
            this.pC(true, var3, var62, var64, var37, var8, var52, var34);
         }

         if (!this.ED) {
            var57 = new InstructionCoordinates(var3.getIndex(), (int)var54.getLastAddress());
            this.Er.recordLineCoordinates(var57);
            int var65 = (int)var54.getEndAddress();
            bfn.Av var71 = var49.pC(var65);
            if (var71 != null) {
               this.pC(var34, var52, var71.kS);
            }

            this.Er.unrecordLineCoordinates();
         }

         if (var6) {
            if (var41.size() >= 1 && var41.get(var41.size() - 1).size() >= 1) {
               IDalvikInstruction var66 = (IDalvikInstruction)var41.get(var41.size() - 1).getLast();
               int var72 = (int)var66.getOffset() + var66.getSize();
               boolean var77 = false;

               for (bfb var25 : var34.getExceptionItems()) {
                  if (var25.A() == var72) {
                     var77 = true;
                     break;
                  }
               }

               if (var77) {
                  var52.pC(var34, var72, true);
                  this.Er.eol();
               }
            }

            var57 = new InstructionCoordinates(var3.getIndex(), (int)var54.getLastAddress());
            this.Er.recordLineCoordinates(var57);
            if (this.ED) {
               this.pC(var34, var52, var34.getExceptionItems());
            }

            this.Er.enablePadding();

            for (IDalvikInstruction var73 : this.A(var2)) {
               int var78 = var73.getOpcode();
               if (var78 == 43) {
                  this.Er.disablePadding();
                  IDalvikInstructionSwitchData var82 = var73.getSwitchData();
                  var52.pC(var34, var82.getOffset(), true);
                  this.Er.eol();
                  this.pC(var82.getOffset(), null);
                  this.Er.pC(bff.xC);
                  int[][] var85 = var82.getElements();
                  int var87 = 0;
                  if (var85.length >= 1) {
                     var87 = var85[0][0];
                     this.Er.space();
                     this.pC(var87);
                  }

                  this.Er.eol();
                  this.Er.enablePadding();
                  this.Er.incrementIndentationLevel();
                  int var89 = var87;

                  for (int[] var94 : var85) {
                     int var95 = (int)var73.getOffset() + var94[1] * 2;
                     var52.pC(var34, var95, false);
                     if (this.sO != null) {
                        String var33 = this.sO.generateAutoCommentForPotentialResourceId(var89);
                        if (var33 != null) {
                           this.Er.space(2);
                           this.Er.appendCommentAuto("# " + var33);
                        }
                     }

                     this.Er.eol();
                     var89++;
                  }

                  this.Er.decrementIndentationLevel();
                  this.Er.pC(bff.ED);
                  this.Er.eol();
               } else if (var78 == 44) {
                  this.Er.disablePadding();
                  IDalvikInstructionSwitchData var81 = var73.getSwitchData();
                  var52.pC(var34, var81.getOffset(), true);
                  this.Er.eol();
                  this.pC(var81.getOffset(), null);
                  this.Er.pC(bff.Sc);
                  this.Er.eol();
                  this.Er.enablePadding();
                  this.Er.incrementIndentationLevel();
                  int var84 = 0;

                  for (int[] var29 : var81.getElements()) {
                     int var30 = var29[0];
                     int var31 = (int)var73.getOffset() + var29[1] * 2;
                     var84 = this.pC(var30, var84, false);
                     this.Er.append(" -> ");
                     var52.pC(var34, var31, false);
                     if (this.sO != null) {
                        String var32 = this.sO.generateAutoCommentForPotentialResourceId(var30);
                        if (var32 != null) {
                           this.Er.space(2);
                           this.Er.appendCommentAuto("# " + var32);
                        }
                     }

                     this.Er.eol();
                  }

                  this.Er.decrementIndentationLevel();
                  this.Er.pC(bff.ah);
                  this.Er.eol();
               } else {
                  if (var78 != 38) {
                     throw new JebRuntimeException("Unknown pseudo-opcode: " + var78);
                  }

                  this.Er.disablePadding();
                  IDalvikInstructionArrayData var80 = var73.getArrayData();
                  var52.pC(var34, var80.getOffset(), true);
                  this.Er.eol();
                  this.pC(var80.getOffset(), null);
                  this.Er.pC(bff.eP);
                  byte[][] var83 = var80.getElements();
                  if (var83.length == 0) {
                     this.Er.append(" 0");
                  } else {
                     this.Er.append(" " + Integer.toString(var83[0].length));
                  }

                  if (!this.fI) {
                     this.Er.append(" x 0x" + Formatter.toHexString(var83.length, true));
                  }

                  this.Er.eol();
                  this.Er.enablePadding();
                  this.Er.incrementIndentationLevel();
                  long[] var26 = var80.asArrayOfLongs();

                  for (int var27 = 0; var27 < var26.length; var27++) {
                     long var28 = var26[var27];
                     if (var28 >= 0L) {
                        this.Er.append("0x" + Formatter.toHexString(var28, true));
                     } else {
                        this.Er.append("-0x" + Formatter.toHexString(-var28, true));
                     }

                     this.Er.eol();
                  }

                  this.Er.decrementIndentationLevel();
                  this.Er.pC(bff.UO);
                  this.Er.eol();
               }
            }

            this.Er.disablePadding();
            this.Er.unrecordLineCoordinates();

            for (Entry var74 : var44.entrySet()) {
               if ((Integer)var74.getKey() > var62) {
                  this.pC(true, var3, (Integer)var74.getKey(), (byte[])var74.getValue(), var37, var8, var52, var34);
               }
            }

            if (this.mv) {
               bfm.Av var69 = var52.pC();
               if (!var69.kS.isEmpty()) {
                  Object[] var10000 = new Object[]{var3.getSignature(true)};

                  for (int var79 : var69.kS) {
                     var10000 = new Object[]{var79};
                  }
               }
            }
         }
      }
   }

   private void pC(bev var1, bfm var2, Collection var3) {
      this.Er.enablePadding();

      for (bfb var5 : var3) {
         int var6 = var5.getTryAddress();
         int var7 = var5.A();

         for (bfa var9 : var5.getHandlers()) {
            if (var9.isTyped()) {
               this.Er.pC(bff.NS);
               this.Er.space();
               this.Er.UT(this.rl, var9.A());
               this.Er.space();
            } else {
               this.Er.pC(bff.vP);
               this.Er.space();
            }

            this.Er.append("{");
            var2.pC(var1, var6, false);
            this.Er.append(" .. ");
            var2.pC(var1, var7, false);
            this.Er.append("} ");
            var2.pC(var1, var9.getAddress(), false);
            this.Er.eol();
         }
      }

      this.Er.disablePadding();
   }

   private int pC(int var1) {
      return this.pC(var1, 0, true);
   }

   private int pC(int var1, int var2, boolean var3) {
      long var4 = var1 & 4294967295L;
      if (var2 == 0) {
         var2 = this.rl.kS(var4);
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

      this.Er.appendAndRecord(var6, ItemClassIdentifiers.NUMBER, this.rl.pC(Long.valueOf(var4)));
      return var2;
   }

   private void pC(IDalvikInstruction var1, bfu var2, int var3, IDexDebugInfo var4, bfm var5, bev var6) {
      long var7 = var1.getOffset();
      int var9 = (int)var7;
      InstructionCoordinates var10 = new InstructionCoordinates(var2.getIndex(), var9);
      this.Er.recordLineCoordinates(var10);
      int var11 = var1.getParameterCount();
      if (var4 != null) {
         this.Er.enablePadding();
         IDexDebugLine var12 = var4.getLineInfo(var9 / 2);
         if (var12.getSourceIndex() >= 0) {
            this.Er.pC(bfe.pC);
            this.Er.space();
            this.Er.pC(this.rl, var12.getSourceIndex());
            this.Er.eol();
         }

         if (var12.isPrologueEnd()) {
            this.Er.pC(bfe.A);
            this.Er.eol();
         }

         if (var12.isEpilogueBegin()) {
            this.Er.pC(bfe.kS);
            this.Er.eol();
         }

         if (this.gp && var12.getLineNumber() >= 0) {
            this.Er.pC(bfe.wS);
            this.Er.append(" " + var12.getLineNumber());
            this.Er.eol();
         }

         for (IDexDebugVariable var14 : var12.getVariables()) {
            this.Er.pC(bfe.UT);
            this.Er.append(" v" + var14.getRegister() + ", \"" + this.rl.pC(var14.getNameIndex(), "", true) + "\":" + this.rl.pC(var14.getTypeIndex(), false));
            this.Er.eol();
         }

         for (int var30 : var12.getVariablesEnd()) {
            this.Er.pC(bfe.E);
            this.Er.append(" v" + var30);
            this.Er.eol();
         }

         for (int var31 : var12.getVariablesRestart()) {
            this.Er.pC(bfe.sY);
            this.Er.append(" v" + var31);
            this.Er.eol();
         }

         this.Er.disablePadding();
      }

      this.A(var10);
      this.pC(var9, var1.getCode());
      String var26 = var1.getMnemonic();
      int var29 = var1.getOpcode();
      this.Er.appendAndRecord(var26, ItemClassIdentifiers.MNEMONIC, var26.hashCode());
      if (var11 >= 1) {
         int var32 = Math.max(1, 20 - var26.length());
         this.Er.space(var32);
      }

      boolean var33 = false;
      byte var15 = 1;
      if (this.fI
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
               this.Er.append("{}, ");
            } else if (var17 == 0) {
               this.Er.append("{");
            } else if (var17 == var11 - var15) {
               this.Er.append("}");
            }
         } else {
            var18 = var1.getParameterType(var17);
            var19 = var1.getParameterValue(var17);
         }

         if (var17 >= 1) {
            this.Er.appendParameterSeparator();
         }

         if (var18 == 0) {
            String var21 = this.pC((int)var19, var3);
            long var22 = 648518346341351424L | var19;
            this.Er.appendAndRecord(var21, ItemClassIdentifiers.IDENTIFIER, var22);
         } else if (var18 == 4) {
            String var34 = this.pC((int)(var19 & 65535L), var3);
            String var39 = this.pC((int)(var19 >> 32 & 65535L), var3);
            this.Er.appendAndRecord(var34, ItemClassIdentifiers.IDENTIFIER, var34.hashCode());
            this.Er.append(" .. ");
            this.Er.appendAndRecord(var39, ItemClassIdentifiers.IDENTIFIER, var39.hashCode());
         } else if (var18 == 1) {
            long var35 = var19;
            int var23 = this.rl.kS(var19);
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

            if (this.sO != null && (var35 & -4294967296L) == 0L) {
               String var25 = this.sO.generateAutoCommentForPotentialResourceId((int)var35);
               if (var25 != null) {
                  var16 = this.pC(var16, var25);
               }
            }

            if (var24 == null) {
               var24 = Long.toString(var35);
            }

            this.Er.appendAndRecord(var24, ItemClassIdentifiers.NUMBER, this.rl.pC(Long.valueOf(var19)));
         } else if (var18 == 3) {
            int var36 = (int)var1.getOffset() + (int)var19 * 2;
            var5.pC(var6, var36, false);
         } else if (var18 != 2 && var18 != 5) {
            JebRuntimeException var38 = new JebRuntimeException("Unknown parameter type: " + var18);
            JebCoreService.notifySilentExceptionToClient(var38);
         } else {
            int var37 = (int)var19;
            int var40 = var18 == 2 ? var1.getParameterFirstIndexType() : var1.getParameterSecondIndexType();
            switch (var40) {
               case 16:
                  this.Er.pC(this.rl, var37);
                  break;
               case 17:
                  this.Er.UT(this.rl, var37);
                  break;
               case 18:
                  this.Er.kS(this.rl, var37);
                  break;
               case 19:
                  this.Er.A(this.rl, var37);
                  if (this.Sc) {
                     int var42 = this.rl.Er().pC(var2.getIndex(), var1.getOffset());
                     if (var42 >= 0 && var42 != var37) {
                        String var43 = Strings.ff("actual call site: %s", this.rl.sY(var42).getSignature());
                        var16 = this.pC(var16, var43);
                     }
                  }
                  break;
               case 20:
                  this.Er.wS(this.rl, var37);
                  break;
               case 21:
                  this.pC(this.Er, var37);
                  break;
               case 22:
                  this.A(this.Er, var37);
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
                  this.Er.append("inline@" + var37);
                  break;
               case 33:
                  this.Er.append("vtaboff@" + var37);
                  break;
               case 34:
                  this.Er.append("fieldoff@" + var37);
            }
         }
      }

      if (var1.isOptimized()) {
         var16 = this.pC(var16, "optimized instruction");
      }

      this.pC(var10, var16 == null ? null : var16.toString());
      this.Er.eol(var10);
      this.Er.unrecordLineCoordinates();
   }

   StringBuilder pC(StringBuilder var1, String var2) {
      if (var1 == null) {
         var1 = new StringBuilder(var2);
      } else {
         var1.append("\n").append(var2);
      }

      return var1;
   }

   private void pC(boolean var1, bfu var2, int var3, byte[] var4, int var5, IDexDebugInfo var6, bfm var7, bev var8) {
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
               this.Er.appendCommentAuto("# " + S.L("(Padding)"));
            } else {
               this.Er.appendCommentAuto("# " + Strings.ff(S.L("(Gap) 0x%X bytes"), var9));
            }

            this.Er.eol();
            if (this.xC) {
               beo var16 = new beo(null, null, null, 0, true, true);
               var16.pC(var4);
               int var12 = 0;

               while (var12 < var9) {
                  bek var13 = null;

                  try {
                     var13 = var16.pC();
                  } catch (Exception var15) {
                     UT.catchingSilent(var15);
                  }

                  if (var13 == null) {
                     this.pC(var12, Arrays.copyOfRange(var4, var12, var12 + 1));
                     this.Er.space(2);
                     this.Er.appendCommentAuto("# " + S.L("(Parsing failed)"));
                     this.Er.eol();
                     var16.pC(1);
                     var12++;
                  } else {
                     var13.pC(var3);
                     this.pC(var13, var2, var5, var6, var7, var8);
                     var16.pC(var13.getSize());
                     var12 += var13.getSize();
                  }
               }
            } else if (this.sY) {
               this.pC(var3, var4);
               this.Er.eol();
            }
         }
      }
   }

   private String pC(int var1, int var2) {
      return this.oT && var1 >= var2 ? "p" + (var1 - var2) : "v" + var1;
   }

   private void pC(int var1, byte[] var2) {
      this.A(var1);
      this.pC(var2);
   }

   private void A(int var1) {
      if (this.E) {
         if (var1 < 0) {
            this.Er.space(10);
         } else {
            String var2 = Formatter.toHexString(var1, true, 8);
            this.Er.appendAndRecord(var2, ItemClassIdentifiers.ADDRESS);
            this.Er.space(2);
         }
      }
   }

   private void pC(byte[] var1) {
      if (this.sY) {
         if (var1 == null) {
            this.Er.space(24);
         } else {
            String var2 = Formatter.formatBinaryLine(var1, 0, var1.length, 8).toString();
            this.Er.appendAndRecord(var2, ItemClassIdentifiers.BYTECODE);
         }
      }
   }

   private void pC(IDexAnnotationItem var1) {
      IDexAnnotation var2 = var1.getAnnotation();
      this.Er.pC(bff.wS);
      this.Er.space();
      this.kS(var1.getVisibility());
      this.pC(var2);
      this.Er.pC(bff.UT);
      this.Er.eol();
   }

   private void pC(IDexAnnotation var1) {
      this.Er.UT(this.rl, var1.getTypeIndex());
      this.Er.eol();
      this.Er.incrementIndentationLevel();

      for (IDexAnnotationElement var3 : var1.getElements()) {
         String var4 = this.rl.pC(var3.getNameIndex(), "", true);
         IDexValue var5 = var3.getValue();
         this.Er.append(var4);
         this.Er.append(" = ");
         this.pC(var5);
         this.Er.eol();
      }

      this.Er.decrementIndentationLevel();
   }

   private void kS(int var1) {
      if (var1 == 0) {
         this.Er.append("build ");
      } else if (var1 == 1) {
         this.Er.append("runtime ");
      } else if (var1 == 2) {
         this.Er.append("system ");
      } else {
         this.Er.append("unknown-visibility ");
      }
   }

   private void A(IDexAnnotation var1) {
      this.Er.pC(bff.E);
      this.Er.space();
      this.pC(var1);
      this.Er.pC(bff.sY);
      this.Er.eol();
   }

   private void pC(IDexValue var1) {
      switch (var1.getType()) {
         case 0:
            this.Er.append("0x" + Formatter.toHexString(var1.getByte(), true));
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
            this.Er.append("<unsupported_value_" + var1.getType() + ">");
            break;
         case 2:
            this.Er.append("0x" + Formatter.toHexString(var1.getShort(), true));
            break;
         case 3:
            this.Er.append("'" + Formatter.escapeCharacter(var1.getChar(), !this.fI) + "'");
            break;
         case 4:
            this.Er.append("0x" + Formatter.toHexString(var1.getInt(), true));
            break;
         case 6:
            this.Er.append("0x" + Formatter.toHexString(var1.getLong(), true) + "L");
            break;
         case 16:
            this.Er.append(Strings.ff("%ff", var1.getFloat()));
            break;
         case 17:
            this.Er.append(Strings.ff("%f", var1.getDouble()));
            break;
         case 21:
            this.Er.pC(this.rl, this.rl.UT(var1.getMethodTypeIndex()));
            break;
         case 22:
            this.A(this.Er, var1.getMethodHandleIndex());
            break;
         case 23:
            this.Er.pC(this.rl, var1.getStringIndex());
            break;
         case 24:
            this.Er.UT(this.rl, var1.getTypeIndex());
            break;
         case 25:
            this.Er.kS(this.rl, var1.getFieldIndex());
            break;
         case 26:
            this.Er.A(this.rl, var1.getMethodIndex());
            break;
         case 27:
            this.Er.kS(this.rl, var1.getEnumIndex());
            break;
         case 28:
            this.Er.append("{");
            this.Er.eol();
            this.Er.incrementIndentationLevel();
            int var2 = 0;
            List var3 = var1.getArray();

            for (IDexValue var5 : var3) {
               this.pC(var5);
               if (++var2 < var3.size()) {
                  this.Er.append(",");
               }

               this.Er.eol();
            }

            this.Er.decrementIndentationLevel();
            this.Er.append("}");
            break;
         case 29:
            this.A(var1.getAnnotation());
            break;
         case 30:
            this.Er.append("null");
            break;
         case 31:
            this.Er.append(Boolean.toString(var1.getBoolean()));
      }
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.dex.Sv var1) {
      if (this.ah && var1.isRenamed()) {
         this.Er.appendCommentAuto("# " + Strings.ff(S.L("original signature: %s"), var1.getSignature(false)));
         this.Er.eol();
      }
   }

   public static void pC(bfj var0, int var1, int var2, int var3) {
      int[] var4 = new int[]{0};
      pC(var0, var4, var2, 1, bfk.cX);
      pC(var0, var4, var2, 2, bfk.UW);
      pC(var0, var4, var2, 4, bfk.PR);
      pC(var0, var4, var2, 8, bfk.OB);
      pC(var0, var4, var2, 16, bfk.Ab);
      pC(var0, var4, var2, 4096, bfk.Pe);
      if (var1 == 0) {
         pC(var0, var4, var2, 1024, bfk.pC);
         pC(var0, var4, var2, 512, bfk.mv);
         pC(var0, var4, var2, 8192, bfk.A);
         pC(var0, var4, var2, 16384, bfk.eP);
      } else if (var1 == 1) {
         pC(var0, var4, var2, 64, bfk.JF);
         pC(var0, var4, var2, 128, bfk.xM);
         pC(var0, var4, var2, 16384, bfk.eP);
      } else if (var1 == 2) {
         pC(var0, var4, var2, 32, bfk.Bf);
         pC(var0, var4, var2, 64, bfk.E);
         pC(var0, var4, var2, 128, bfk.Kq);
         pC(var0, var4, var2, 256, bfk.os);
         pC(var0, var4, var2, 1024, bfk.pC);
         pC(var0, var4, var2, 2048, bfk.pF);
         pC(var0, var4, var2, 65536, bfk.WR);
         pC(var0, var4, var2, 131072, bfk.vP);
      }

      if (var3 == 1 || var3 == -1 && var4[0] > 0) {
         var0.space();
      }
   }

   private static void pC(bfj var0, int[] var1, int var2, int var3, bfk var4) {
      if (var4 != null && (var2 & var3) != 0) {
         if (var1[0] > 0) {
            var0.space();
         }

         var0.pC(var4);
         var1[0]++;
      }
   }

   void pC(bfj var1, int var2) {
      bfr var3 = this.rl.ld(var2);
      if (var3 == null) {
         var1.append(Strings.ff("INVALID_CALLSITE_%d", var2));
      } else {
         this.pC(var1, var3);
      }
   }

   void pC(bfj var1, bfr var2) {
      int var3 = var2.getLinkerMethodHandleIndex();
      bfx var4 = var2.kS();
      bfw var5 = var2.wS();
      var1.append("call_site_" + var2.getIndex() + "(");
      var1.pC(this.rl, var4.getIndex());
      var1.appendParameterSeparator();
      var1.wS(this.rl, var5.pC());

      for (int var6 = 3; var6 < var2.getCallSiteValues().size(); var6++) {
         var1.appendParameterSeparator();
         beg var7 = var2.pC(var6);
         this.pC(var7);
      }

      var1.append(")");
      var1.append("@");
      this.pC(var1, var3, false);
   }

   void A(bfj var1, int var2) {
      this.pC(var1, var2, true);
   }

   void pC(bfj var1, int var2, boolean var3) {
      bfv var4 = this.rl.gp(var2);
      if (var4 == null) {
         var1.append(Strings.ff("INVALID_METHOD_HANDLE_%d", var2));
      } else {
         this.pC(var1, var4, var3);
      }
   }

   void pC(bfj var1, bfv var2, boolean var3) {
      DexMethodHandleType var4 = var2.getMethodHandleType();
      if (var3) {
         var1.append(var4.toString());
         var1.append("@");
      }

      if (var4.isMethodInvoker()) {
         var1.A(this.rl, var2.getFieldOrMethodIndex());
      } else if (var4.isFieldAccessor()) {
         var1.kS(this.rl, var2.getFieldOrMethodIndex());
      } else {
         var1.append(var2.generate(true));
      }
   }

   @Override
   public IDexUnit getUnit() {
      return this.rl;
   }

   @Override
   public IDexItemToAnchor getCodeItemToAnchor() {
      return this.FE;
   }

   @Override
   public boolean hasBinaryRepresentation() {
      return true;
   }

   @Override
   public IBinaryRepresentation getBinaryRepresentation() {
      if (this.UW == null) {
         synchronized (this) {
            this.UW = new bfg.Av();
         }
      }

      return this.UW;
   }

   private class Av implements IBinaryRepresentation {
      Map pC = new HashMap();
      TreeMap A = new TreeMap();
      byte[] kS;

      Av() {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         int var3 = 0;

         for (bfu var5 : bfg.this.rl.getMethods()) {
            if (var5.isInternal() && var5.A().pC() != null) {
               bev var6 = var5.A().pC();
               byte[] var7 = bfg.this.rl.getDexEntry(var6.getDexEntryIndex()).getData();
               int var8 = var6.getInstructionsOffset();
               int var9 = var6.getInstructionsSize();
               var2.write(var7, var8, var9);
               this.A.put(var3, var5.getIndex());
               this.pC.put(var5.getIndex(), var3);
               var3 += var9;
            }
         }

         this.kS = var2.toByteArray();
      }

      @Override
      public long getBaseOffsetHint() {
         return 0L;
      }

      @Override
      public int read(long var1, int var3, byte[] var4, int var5) {
         if (var1 >= 0L && var1 < this.kS.length) {
            int var6 = (int)var1 + var3;
            if (var6 > this.kS.length) {
               var3 = this.kS.length - (int)var1;
            }

            System.arraycopy(this.kS, (int)var1, var4, var5, var3);
            return var3;
         } else {
            return 0;
         }
      }

      @Override
      public long find(long var1, long var3, byte[] var5, byte[] var6) {
         if (Longs.compareUnsigned(var1, var3) <= 0) {
            if (var3 == -1L) {
               var3 = this.kS.length;
            }

            return Bytes.indexOf(this.kS, (int)var1, (int)var3, var5, 0, var5.length, var6);
         } else {
            if (var1 == -1L) {
               var1 = this.kS.length;
            }

            return Bytes.lastIndexOf(this.kS, (int)var1, var5, var6);
         }
      }

      @Override
      public ICoordinates convertOffsetToCoordinates(long var1) {
         Integer var3 = (Integer)this.A.floorKey((int)var1);
         if (var3 == null) {
            return null;
         } else {
            int var4 = (int)var1 - var3;
            int var5 = (Integer)this.A.get(var3);
            InstructionCoordinates var6 = new InstructionCoordinates(var5, var4);
            String var7 = bfg.this.rl.A().pC(var6);
            return bfg.this.addressToCoordinates(var7);
         }
      }

      @Override
      public long convertCoordinatesToOffset(ICoordinates var1) {
         String var2 = bfg.this.coordinatesToAddress(var1);
         if (var2 == null) {
            return -1L;
         } else {
            ICodeCoordinates var3 = bfg.this.rl.A().pC(var2);
            Integer var4 = CodeCoordinatesUtil.getMethodObjectIndex(var3);
            if (var4 == null) {
               return -1L;
            } else {
               Integer var5 = (Integer)this.pC.get(var4);
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
