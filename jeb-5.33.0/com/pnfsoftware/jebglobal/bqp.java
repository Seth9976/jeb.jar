package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.IrregularFlowData;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexMethodHandleType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexCallSite;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodHandle;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPrototype;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecConversionException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDAllocObjectInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDElement;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDNewArrayInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaOperator;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.concurrent.Watchdog;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Integers;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class bqp {
   private static final ILogger pC = GlobalLog.getLogger(bqp.class, Integer.MAX_VALUE);
   private boolean kS = false;
   private bpx wS;
   private bpr UT;
   private cdk E;
   private cdg sY;
   private IDexUnit ys;
   private IDexMethodData ld;
   private IDexMethod gp;
   private CFG oT;
   private IDFA fI;
   private Map WR;
   private Set NS = new HashSet();
   private IJavaType vP;
   private IJavaType xC;
   private IJavaType ED;
   private bqp.Av Sc = new bqp.Av();
   private int ah;
   private List eP;
   private Set UO = new HashSet();
   private Map Ab;
   private Map rl;
   private boolean z;
   private List Ek;
   private Set hK;
   private Set Er = new TreeSet();
   private Set FE = new TreeSet();
   private Map Aj;
   private bfc EX;
   private List LM;
   private Watchdog mv;
   private static String sO = "_zthdgtehsuiesuuwthheyshqoplejsmnxyehhte_";
   private int os = 0;
   private int Cu = 0;
   private static Set UW;

   public bqp(bpx ws) {
      if (ws == null) {
         throw new IllegalArgumentException();
      } else {
         this.wS = ws;
         this.UT = (bpr)ws.getGlobalContext();
         this.ys = ws.getDex();
         this.gp = ws.getMethod();
         this.mv = ws.getWatchdog();
         this.ld = this.gp.getData();
         this.E = this.UT.kS();
         this.sY = this.UT.wS();
         this.vP = this.E.createType("Ljava/lang/Object;");
         this.xC = this.E.createType("Ljava/lang/Class;");
         this.ED = this.E.createType("Ljava/lang/String;");
         this.oT = this.ld.getCodeItem().getControlFlowGraph();
         this.fI = this.oT.createDataFlowAnalysisHelperObject();
         this.WR = this.oT.getAddressBlockMap();
         this.os = 0;
         String name = this.gp.getName(false);
         int index;
         if ((index = name.indexOf(sO)) >= 0) {
            String substring = name.substring(index + sO.length());
            if (substring.startsWith("crash")) {
               int stringToInt = Conversion.stringToInt(substring.substring(5), 1);
               if (stringToInt == 1 || stringToInt == 2) {
                  this.os = stringToInt;
               }
            } else if (substring.startsWith("stall")) {
               String substring2 = substring.substring(5);
               this.os = 3;
               this.Cu = Conversion.stringToInt(substring2, 0);
            } else if (substring.startsWith("loop")) {
               String substring3 = substring.substring(4);
               this.os = 4;
               this.Cu = Conversion.stringToInt(substring3, 0);
            }
         }
      }
   }

   public void pC(boolean b, boolean b2, boolean z) throws DexDecConversionException {
      if (this.kS) {
         throw new IllegalStateException("Already transformed");
      } else {
         this.z = z;
         if (z) {
            this.ys.addMethod("Ljeb/synthetic/Objects;->alloc(Ljava/lang/Class;)Ljava/lang/Object;");
         }

         this.ah = 0;
         this.Ab = new HashMap();
         this.rl = new HashMap();
         this.Ek = new ArrayList();
         this.hK = new HashSet();
         this.Aj = new HashMap();
         this.EX = new bfc(this.ld.getCodeItem().getExceptionItems());
         if (this.os == 3 || this.os == 4) {
            boolean b3 = this.os == 3;
            this.os = 0;
            int n = this.Cu == 0 ? Integer.MAX_VALUE : this.Cu * 10;

            for (int i = 0; i < n; i++) {
               try {
                  Thread.sleep(100L);
                  if (b3) {
                     Watchdog.verify(this.mv);
                  }
               } catch (InterruptedException var18) {
                  if (b3) {
                     Watchdog.reportInterruption(var18);
                  } else {
                     Thread.currentThread().interrupt();
                  }
               }
            }
         }

         this.eP = new ArrayList();

         for (int j = 0; j < this.oT.size(); j++) {
            this.eP.addAll(this.A(j));
         }

         if (this.eP instanceof ArrayList list) {
            list.trimToSize();
         }

         int n2 = (int)this.oT.get(this.oT.size() - 1).getEndAddress();
         this.Ab.put(n2, this.ah);
         this.rl.put(this.ah, n2);

         for (IDTarget idTarget : this.Sc.pC()) {
            int offset = idTarget.getOffset();
            Integer n3 = (Integer)this.Ab.get(offset);
            if (n3 == null) {
               throw new RuntimeException(Strings.ff("Cannot map Dalvik offset 0x%X to generated IR", offset));
            }

            idTarget.setOffset(n3);
         }

         Iterator iterator2 = this.hK.iterator();

         while (iterator2.hasNext()) {
            Integer n4 = (Integer)this.Ab.get((int)((IDalvikInstruction)iterator2.next()).getOffset());
            if (n4 != null && n4 < this.eP.size()) {
               IDInstruction idInstruction = (IDInstruction)this.eP.get(n4);
               if (idInstruction.getOffset() == n4.intValue() && idInstruction.getOpcode() == DOpcodeType.IR_ASSIGN) {
                  idInstruction.setAssignSource(this.UT.createNull());
               }
            }
         }

         this.hK.clear();
         if (!z && !this.Ek.isEmpty()) {
            pC.warn(S.L("The method cannot be properly decompiled, new-instance instructions remain unmatched"));

            for (IDalvikInstruction dalvikInstruction : this.Ek) {
               pC.warn("%04Xh: %s (%s)", dalvikInstruction.getOffset(), dalvikInstruction.format(null), S.L("unmatched"));
            }
         }

         if (!this.FE.isEmpty()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(
               new RuntimeException("Failed type determination for fill-array-data instructions: " + Integers.formatHexIntegerCollection(this.FE)),
               this.gp.getSignature(false)
            );
         }

         this.LM = new ArrayList();
         if (b) {
            for (BasicBlock basicBlock : this.oT) {
               if (basicBlock.irroutsize() != 0) {
                  int n5 = (int)basicBlock.get(0).getOffset();
                  Integer n6 = (Integer)this.Ab.get(n5);
                  if (n6 == null) {
                     throw new RuntimeException(Strings.ff("Failed to convert Dalvik try-start offset: 0x%X", n5));
                  }

                  int intValue = n6;
                  int n7 = (int)basicBlock.get(basicBlock.size() - 1).getOffset();
                  Integer n8 = (Integer)this.Ab.get(n7);
                  if (n8 == null) {
                     throw new RuntimeException(Strings.ff("Failed to convert Dalvik try-last offset: 0x%X", n7));
                  }

                  int intValue2 = n8;
                  int n9 = 1;

                  while (true) {
                     Integer n10 = (Integer)this.rl.get(n8 + n9);
                     if (n10 == null || n10 != n7) {
                        Iterator iterator5 = basicBlock.getIrregularOutputs().iterator();

                        while (iterator5.hasNext()) {
                           int n11 = (int)((BasicBlock)iterator5.next()).get(0).getOffset();
                           Integer n12 = (Integer)this.Ab.get(n11);
                           if (n12 == null) {
                              throw new RuntimeException(Strings.ff("Failed to convert Dalvik try-handler offset: 0x%X", n11));
                           }

                           this.LM.add(new IrregularFlowData(intValue, intValue2, n12.intValue()));
                        }
                        break;
                     }

                     intValue2++;
                     n9++;
                  }
               }
            }
         }

         this.fI = null;
         this.kS = true;
      }
   }

   public List pC() {
      if (!this.kS) {
         throw new RuntimeException();
      } else {
         return this.eP;
      }
   }

   public List A() {
      if (!this.kS) {
         throw new RuntimeException();
      } else {
         return this.LM;
      }
   }

   public Map kS() {
      if (!this.kS) {
         throw new IllegalStateException();
      } else {
         return this.Ab;
      }
   }

   public Set wS() {
      return Collections.unmodifiableSet(this.NS);
   }

   public Map UT() {
      if (!this.kS) {
         throw new IllegalStateException();
      } else {
         return this.rl;
      }
   }

   private IDVar pC(int n, IJavaType javaType) {
      return this.wS.createVar(DUtil.createRegisterVarId(n, javaType.isDoubleSlot()));
   }

   private List A(int n) throws DexDecConversionException {
      BasicBlock value = this.oT.get(n);
      List instructions = value.getInstructions();
      ArrayList list = new ArrayList();
      int i = 0;
      IDalvikInstruction dalvikInstruction = null;
      List list2 = null;
      IDalvikInstruction[] array = new IDalvikInstruction[]{null};
      int[] array2 = new int[]{0};
      HashSet set = new HashSet();
      HashSet set2 = new HashSet();

      while (i < instructions.size()) {
         IDalvikInstruction dalvikInstruction2 = (IDalvikInstruction)instructions.get(i);
         int opcode;
         if (this.UO.contains(dalvikInstruction2.getOffset())) {
            opcode = 0;
         } else {
            opcode = dalvikInstruction2.getOpcode();
         }

         IDInstruction idInstruction = null;
         int parameterCount = dalvikInstruction2.getParameterCount();
         int j = -1;
         if (parameterCount >= 1 && dalvikInstruction2.getParameterType(0) == 0) {
            j = (int)dalvikInstruction2.getParameterValue(0);
         }

         int n2 = -1;
         if (parameterCount >= 2 && dalvikInstruction2.getParameterType(1) == 0) {
            n2 = (int)dalvikInstruction2.getParameterValue(1);
         }

         int n3 = -1;
         if (parameterCount >= 3 && dalvikInstruction2.getParameterType(2) == 0) {
            n3 = (int)dalvikInstruction2.getParameterValue(2);
         }

         if (this.os > 0) {
            int os = this.os;
            this.os = 0;
            if (os == 1) {
               throw new RuntimeException("BOOH!");
            }

            if (os == 2) {
               throw new DexDecConversionException(dalvikInstruction2);
            }
         }

         switch (opcode) {
            case 0:
               idInstruction = this.wS.createNop();
               break;
            case 1:
            case 2:
            case 3:
               idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.gp), this.pC(n2, this.E.gp));
               break;
            case 4:
            case 5:
            case 6:
               idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.oT), this.pC(n2, this.E.oT));
               break;
            case 7:
            case 8:
            case 9:
               idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.WR), this.pC(n2, this.E.WR));
            case 10:
            case 11:
            case 12:
               break;
            case 13:
               if (i != 0) {
                  for (int k = 0; k < i; k++) {
                     if (((IDalvikInstruction)instructions.get(k)).getOpcode() != 0) {
                        throw new RuntimeException("Illegal move-exception position");
                     }
                  }
               }

               this.Aj.put(this.ah, j);
               int pc = this.EX.pC((int)value.getAddress());
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_STORE_EXCEPTION, this.pC(j, this.E.pC(pc < 0 ? "Ljava/lang/Throwable;" : this.ys.getType(pc).getSignature(false))), null);
               break;
            case 14:
               idInstruction = this.wS.pC(DOpcodeType.IR_RETURN, null, null);
               break;
            case 15:
               idInstruction = this.wS.pC(DOpcodeType.IR_RETURN, null, this.pC(j, this.E.gp));
               break;
            case 16:
               idInstruction = this.wS.pC(DOpcodeType.IR_RETURN, null, this.pC(j, this.E.oT));
               break;
            case 17:
               idInstruction = this.wS.pC(DOpcodeType.IR_RETURN, null, this.pC(j, this.E.WR));
               break;
            case 18:
            case 19:
            case 20:
            case 21:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.gp), this.UT.createConstant((int)dalvikInstruction2.getParameterValue(1), this.E.gp));
               break;
            case 22:
            case 23:
            case 24:
            case 25:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.oT), this.UT.createConstant(dalvikInstruction2.getParameterValue(1), this.E.oT));
               break;
            case 26:
            case 27:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.ED), this.UT.createString(this.UT.createIndex((int)dalvikInstruction2.getParameterValue(1))));
               break;
            case 28:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.xC),
                     this.UT.createStaticField(null, this.xC, this.ys.getType((int)dalvikInstruction2.getParameterValue(1)).getSignature(false), "class")
                  );
               break;
            case 29:
               idInstruction = this.wS.pC(DOpcodeType.IR_MONITOR_ENTER, null, this.pC(j, this.E.WR));
               break;
            case 30:
               idInstruction = this.wS.pC(DOpcodeType.IR_MONITOR_EXIT, null, this.pC(j, this.E.WR));
               break;
            case 31:
            case 511:
               IJavaType type = this.E.parseType(this.ys.getType((int)dalvikInstruction2.getParameterValue(1)).getSignature(false));
               idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, this.pC(j, type), this.UT.createCastOperation(type, this.pC(j, this.E.WR)));
               break;
            case 32:
            case 767:
               int n22 = (int)dalvikInstruction2.getParameterValue(2);
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.E.A),
                     this.UT
                        .createOperation(
                           this.E.A,
                           this.pC(n2, this.E.WR),
                           this.kS(opcode),
                           this.UT.createReferenceType(this.UT.createIndex(n22), this.E.parseType(this.ys.getType(n22).getSignature(false)))
                        )
                  );
               break;
            case 33:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.E), this.UT.createInstanceField(this.pC(n2, this.E.WR), null, this.E.E, "length"));
               break;
            case 34:
            case 1023:
               Object o;
               if (this.z) {
                  o = this.UT.createAllocObjectInfo(this.E.parseType(this.ys.getType((int)dalvikInstruction2.getParameterValue(1)).getSignature(false)));
               } else {
                  this.Ek.add(dalvikInstruction2);
                  o = this.UT.createConstant(0L, this.E.WR);
               }

               idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.WR), (IDElement)o);
               break;
            case 35:
            case 1279:
               IJavaType type2 = this.E.parseType(this.ys.getType((int)dalvikInstruction2.getParameterValue(2)).getSignature(false));
               if (type2.getDimensions() <= 0) {
                  throw new RuntimeException("Expected an array type for new-array");
               }

               List list3 = null;
               IDalvikInstruction dalvikInstruction4 = null;
               if (i + 1 < value.size()) {
                  IDalvikInstruction dalvikInstruction5 = (IDalvikInstruction)value.get(i + 1);
                  if (dalvikInstruction5.getOpcode() == 38 && (int)dalvikInstruction5.getParameterValue(0) == j) {
                     dalvikInstruction4 = dalvikInstruction5;
                  }
               }

               if (dalvikInstruction4 != null) {
                  list3 = new ArrayList();
                  byte[] array6 = new byte[8];
                  ByteBuffer wrap = ByteBuffer.wrap(array6);
                  wrap.order(ByteOrder.LITTLE_ENDIAN);
                  IJavaType arrayElementType = type2.getArrayElementType();
                  if (!arrayElementType.isPrimitive()) {
                     throw new RuntimeException("Expected a primitive array element, got: " + arrayElementType);
                  }

                  byte[][] elements = dalvikInstruction4.getArrayData().getElements();

                  for (int n23 = 0; n23 < elements.length; n23++) {
                     for (int n24 = 0; n24 < elements[n23].length; n24++) {
                        array6[n24] = elements[n23][n24];
                     }

                     list3.add(this.UT.createConstant(wrap.getLong(0), arrayElementType));
                  }

                  this.Er.add((int)dalvikInstruction4.getOffset());
               }

               idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, this.pC(j, type2), this.UT.createNewArrayInfo(type2, this.pC(n2, this.E.fI), list3));
               break;
            case 36:
            case 37:
            case 1535:
               IJavaType type3 = this.E.parseType(this.ys.getType((int)dalvikInstruction2.getParameterValue(0)).getSignature(false));
               IDVar pc8 = this.pC(n, i, type3, array);
               if (pc8 != null) {
                  dalvikInstruction = array[0];
               }

               int[] array8;
               if (opcode == 36) {
                  array8 = new int[dalvikInstruction2.getParameterCount() - 1];

                  for (int n28 = 1; n28 < dalvikInstruction2.getParameterCount(); n28++) {
                     array8[n28 - 1] = (int)dalvikInstruction2.getParameterValue(n28);
                  }
               } else {
                  long parameterValue2 = dalvikInstruction2.getParameterValue(1);
                  int n29 = (int)(parameterValue2 & -1L);
                  int n30 = (int)(parameterValue2 >> 32 & -1L);
                  array8 = new int[n30 - n29 + 1];
                  int n31 = 0;
                  int n32 = n29;

                  while (n32 <= n30) {
                     array8[n31++] = n32++;
                  }
               }

               IJavaType arrayTypeDimensionBelow2 = type3.getArrayTypeDimensionBelow();
               ArrayList list4 = new ArrayList(array8.length);

               for (int n33 = 0; n33 < array8.length; n33++) {
                  list4.add(this.pC(array8[n33], arrayTypeDimensionBelow2));
               }

               IDNewArrayInfo newArrayInfo = this.UT.createNewArrayInfo(type3, this.UT.createConstant(list4.size(), this.E.E), list4);
               if (pc8 != null) {
                  idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, pc8, newArrayInfo);
               } else {
                  idInstruction = this.wS.pC(DOpcodeType.IR_INVOKE, null, newArrayInfo);
               }
               break;
            case 38:
               int n25 = (int)dalvikInstruction2.getOffset();
               if (this.Er.contains(n25)) {
                  idInstruction = this.wS.createNop();
               } else {
                  IJavaType javaType2 = new bqp.Sv().pC(dalvikInstruction2);
                  if (javaType2 == null) {
                     this.FE.add(n25);
                     byte[][] elements2 = dalvikInstruction2.getArrayData().getElements();
                     if (elements2.length >= 1) {
                        String s2 = null;
                        int length = elements2[0].length;
                        if (length == 1) {
                           s2 = "[B";
                        } else if (length == 2) {
                           s2 = "[S";
                        } else if (length == 4) {
                           s2 = "[I";
                        } else if (length == 8) {
                           s2 = "[J";
                        }

                        if (s2 != null) {
                           javaType2 = this.E.parseType(s2);
                        }
                     }
                  }

                  if (javaType2 == null) {
                     idInstruction = this.wS.createNop();
                  } else {
                     IJavaType arrayTypeDimensionBelow = javaType2.getArrayTypeDimensionBelow();
                     byte[] array7 = new byte[8];
                     ByteBuffer wrap2 = ByteBuffer.wrap(array7);
                     wrap2.order(ByteOrder.LITTLE_ENDIAN);
                     byte[][] elements3 = dalvikInstruction2.getArrayData().getElements();
                     list2 = new ArrayList(elements3.length);

                     for (int n26 = 0; n26 < elements3.length; n26++) {
                        for (int n27 = 0; n27 < elements3[n26].length; n27++) {
                           array7[n27] = elements3[n26][n27];
                        }

                        IDInstruction pc7 = this.wS
                           .pC(
                              DOpcodeType.IR_ASSIGN,
                              this.UT.createArrayElt(this.pC(j, javaType2), this.UT.createConstant(n26, this.E.E), arrayTypeDimensionBelow),
                              this.UT.createConstant(wrap2.getLong(0), arrayTypeDimensionBelow)
                           );
                        if (idInstruction == null) {
                           idInstruction = pc7;
                        } else {
                           list2.add(pc7);
                        }
                     }
                  }
               }
               break;
            case 39:
               idInstruction = this.wS.pC(DOpcodeType.IR_THROW, null, this.pC(j, this.E.WR));
               break;
            case 40:
            case 41:
            case 42:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_JUMP, this.Sc.pC((int)dalvikInstruction2.getOffset() + 2 * (int)dalvikInstruction2.getParameterValue(0)), null);
               break;
            case 43:
            case 44:
               int n34 = (int)dalvikInstruction2.getOffset() + dalvikInstruction2.getSize();
               IDSwitchData switchData = this.UT.createSwitchData();

               for (int[] array9 : dalvikInstruction2.getSwitchData().getElements()) {
                  int n36 = (int)dalvikInstruction2.getOffset() + 2 * array9[1];
                  if (n36 != n34) {
                     switchData.addCase(array9[0], this.Sc.pC(n36), false);
                  }
               }

               idInstruction = this.wS.pC(DOpcodeType.IR_SWITCH, switchData, this.pC(j, this.E.fI));
               break;
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
               int opcode2 = -1;
               if (i + 1 < instructions.size()) {
                  dalvikInstruction = (IDalvikInstruction)instructions.get(i + 1);
                  opcode2 = dalvikInstruction.getOpcode();
                  if (opcode2 >= 56 && opcode2 <= 61) {
                     if (!this.fI.checkSingleUse(dalvikInstruction2.getOffset(), j, dalvikInstruction.getOffset())) {
                        dalvikInstruction = null;
                     }
                  } else {
                     dalvikInstruction = null;
                  }
               }

               if (dalvikInstruction != null) {
                  int n37 = (int)dalvikInstruction.getOffset() + 2 * (int)dalvikInstruction.getParameterValue(1);
                  if (n37 != dalvikInstruction.getOffset() + dalvikInstruction.getSize()) {
                     IJavaType ws = this.wS(opcode);
                     IDOperation idOperation;
                     if ((opcode == 47 || opcode == 45) && (opcode2 == 58 || opcode2 == 61)
                        || (opcode == 48 || opcode == 46) && (opcode2 == 60 || opcode2 == 59)) {
                        IJavaOperator javaOperator = this.kS(opcode2);
                        if (opcode2 == 58) {
                           javaOperator = this.sY.z;
                        }

                        if (opcode2 == 61) {
                           javaOperator = this.sY.Ek;
                        }

                        if (opcode2 == 60) {
                           javaOperator = this.sY.hK;
                        }

                        if (opcode2 == 59) {
                           javaOperator = this.sY.rl;
                        }

                        idOperation = this.UT
                           .createPredicate(JavaOperatorType.LOG_NOT, null, this.UT.createPredicate(this.pC(n2, ws), javaOperator, this.pC(n3, ws)));
                     } else {
                        idOperation = this.UT.createPredicate(this.pC(n2, ws), this.kS(opcode2), this.pC(n3, ws));
                     }

                     idInstruction = this.wS.pC(DOpcodeType.IR_JCOND, this.Sc.pC(n37), idOperation);
                  }
               } else {
                  IDexMethod dexMethod = null;
                  if (opcode == 45 || opcode == 46) {
                     dexMethod = this.ys.addMethod("Ljava/lang/Float;->compare(FF)I");
                  } else if (opcode == 47 || opcode == 48) {
                     dexMethod = this.ys.addMethod("Ljava/lang/Double;->compare(DD)I");
                  } else if (opcode == 49) {
                     dexMethod = this.ys.addMethod("Ljava/lang/Long;->compare(JJ)I");
                  }

                  if (dexMethod == null) {
                     throw new RuntimeException(Strings.ff("Need a compare() method - it seems none was generate for opcode 0x%X", opcode));
                  }

                  IJavaType[] fullPrototype2 = JavaTypeUtil.parseFullPrototype(dexMethod.getPrototype(), this.E);
                  idInstruction = this.wS
                     .pC(
                        DOpcodeType.IR_ASSIGN,
                        this.pC(j, fullPrototype2[0]),
                        this.UT
                           .createCallInfo(
                              this.UT.createIndex(dexMethod.getIndex()),
                              new IDExpression[]{this.pC(n2, fullPrototype2[1]), this.pC(n3, fullPrototype2[2])},
                              fullPrototype2[0],
                              dexMethod.getSignature(false),
                              DInvokeType.STATIC
                           )
                     );
               }
               break;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
               int n38 = (int)dalvikInstruction2.getOffset() + dalvikInstruction2.getSize();
               int n39 = (int)dalvikInstruction2.getOffset() + 2 * (int)dalvikInstruction2.getParameterValue(2);
               if (n39 != n38) {
                  idInstruction = this.wS
                     .pC(DOpcodeType.IR_JCOND, this.Sc.pC(n39), this.UT.createPredicate(this.pC(j, this.E.gp), this.kS(opcode), this.pC(n2, this.E.gp)));
               } else {
                  idInstruction = this.wS.pC(DOpcodeType.IR_JUMP, this.Sc.pC(n38), null);
               }
               break;
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
               int n40 = (int)dalvikInstruction2.getOffset() + dalvikInstruction2.getSize();
               int n41 = (int)dalvikInstruction2.getOffset() + 2 * (int)dalvikInstruction2.getParameterValue(1);
               if (n41 != n40) {
                  idInstruction = this.wS
                     .pC(
                        DOpcodeType.IR_JCOND,
                        this.Sc.pC(n41),
                        this.UT.createPredicate(this.pC(j, this.E.gp), this.kS(opcode), this.UT.createConstant(0L, this.E.gp))
                     );
               } else {
                  idInstruction = this.wS.pC(DOpcodeType.IR_JUMP, this.Sc.pC(n40), null);
               }
               break;
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
               IJavaType ws2 = this.wS(opcode);
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, (IJavaType)(ws2.isSmallInt() ? this.E.fI : ws2)),
                     this.UT.createArrayElt(this.pC(n2, this.E.WR), this.pC(n3, this.E.fI), ws2)
                  );
               break;
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
               IJavaType ws3 = this.wS(opcode);
               idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, this.UT.createArrayElt(this.pC(n2, this.E.WR), this.pC(n3, this.E.fI), ws3), this.pC(j, ws3));
               break;
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 1791:
            case 2047:
            case 2303:
            case 2559:
            case 2815:
            case 3071:
            case 3327:
            case 3583:
            case 3839:
            case 4095:
            case 4351:
            case 4607:
            case 4863:
            case 5119:
               int n42 = (int)dalvikInstruction2.getParameterValue(2);
               IDexField field = this.ys.getField(n42);
               String name2 = field.getName(false);
               IJavaType type4 = this.E.parseType(field.getFieldTypeSignature(false));
               IDVar pc9 = this.pC(j, type4);
               IDInstanceField instanceField = this.UT
                  .createInstanceField(this.pC(n2, this.E.pC(field.getClassTypeSignature(false))), this.UT.createIndex(n42), type4, name2);
               if ((opcode < 82 || opcode > 88) && (opcode < 1791 || opcode > 3327)) {
                  idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, instanceField, pc9);
               } else {
                  idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, pc9, instanceField);
               }
               break;
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 5375:
            case 5631:
            case 5887:
            case 6143:
            case 6399:
            case 6655:
            case 6911:
            case 7167:
            case 7423:
            case 7679:
            case 7935:
            case 8191:
            case 8447:
            case 8703:
               int n43 = (int)dalvikInstruction2.getParameterValue(1);
               IDexField field2 = this.ys.getField(n43);
               IJavaType type5 = this.E.parseType(field2.getFieldTypeSignature(false));
               IDVar pc10 = this.pC(j, type5);
               IDStaticField staticField = this.UT
                  .createStaticField(this.UT.createIndex(n43), type5, field2.getClassTypeSignature(false), field2.getName(false));
               if ((opcode < 96 || opcode > 102) && (opcode < 5375 || opcode > 6911)) {
                  idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, staticField, pc10);
               } else {
                  idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, pc10, staticField);
               }
               break;
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 250:
            case 251:
            case 252:
            case 253:
            case 8959:
            case 9215:
            case 9471:
            case 9727:
            case 9983:
               DInvokeType dInvokeType;
               if (opcode == 110 || opcode == 116 || opcode == 8959) {
                  dInvokeType = DInvokeType.VIRTUAL;
               } else if (opcode == 111 || opcode == 117 || opcode == 9215) {
                  dInvokeType = DInvokeType.SUPER;
               } else if (opcode == 112 || opcode == 118 || opcode == 9471) {
                  dInvokeType = DInvokeType.DIRECT;
               } else if (opcode == 113 || opcode == 119 || opcode == 9727) {
                  dInvokeType = DInvokeType.STATIC;
               } else if (opcode == 114 || opcode == 120 || opcode == 9983) {
                  dInvokeType = DInvokeType.INTERFACE;
               } else if (opcode != 250 && opcode != 251) {
                  if (opcode != 252 && opcode != 253) {
                     throw new RuntimeException("Unsupported invocation type");
                  }

                  dInvokeType = DInvokeType.CUSTOM;
               } else {
                  dInvokeType = DInvokeType.POLYMORPHIC;
               }

               int n4 = -1;
               IDexCallSite callSite = null;
               if (dInvokeType != DInvokeType.CUSTOM) {
                  n4 = (int)dalvikInstruction2.getParameterValue(0);
               } else {
                  int n5 = (int)dalvikInstruction2.getParameterValue(0);
                  callSite = this.ys.getCallSite(n5);
                  IDexMethodHandle linkerMethodHandle = callSite.getLinkerMethodHandle();
                  if (linkerMethodHandle.getMethodHandleType().isMethodInvoker()) {
                     if ("Ljava/lang/invoke/LambdaMetafactory;->metafactory(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;"
                        .equals(this.ys.getMethod(linkerMethodHandle.getFieldOrMethodIndex()).getSignature())) {
                        IDexMethodHandle methodHandle = this.ys.getMethodHandle(callSite.getCallSiteValue(4).getMethodHandleIndex());
                        if (methodHandle.getMethodHandleType().isMethodInvoker()) {
                           n4 = methodHandle.getFieldOrMethodIndex();
                           dInvokeType = DInvokeType.LAMBDA;
                        }
                     } else if (linkerMethodHandle.getMethodHandleType() == DexMethodHandleType.INVOKE_STATIC) {
                        String value2 = callSite.getDynamicMethodName().getValue(false);
                        String generate = callSite.getDynamicMethodPrototype().generate(false);
                        String name = null;
                        if (callSite.getLinkerMethodHandle().getMethodHandleType() == DexMethodHandleType.INVOKE_STATIC) {
                           name = this.ys.getMethod(callSite.getLinkerMethodHandle().getFieldOrMethodIndex()).getName(false);
                        }

                        n4 = this.ys
                           .addMethod(
                              "Ljeb/synthetic/InvokeCustoms;", "CallSite" + n5 + "_`" + value2 + "`" + (name == null ? "" : "_from_`" + name + "`"), generate
                           )
                           .getIndex();
                        dInvokeType = DInvokeType.STATIC;
                     }
                  }
               }

               if (n4 < 0) {
                  throw new RuntimeException("Unsupported conversion for some invoke-custom");
               }

               IDexMethod method = this.ys.getMethod(n4);
               String classTypeSignature = method.getClassTypeSignature(false);
               int n6 = 1;
               IDexPrototype dexPrototype = method.getPrototype();
               if (dInvokeType == DInvokeType.POLYMORPHIC) {
                  int n7 = (int)dalvikInstruction2.getParameterValue(1);
                  n6 = 2;
                  dexPrototype = this.ys.getPrototype(n7);
               } else if (dInvokeType == DInvokeType.LAMBDA) {
                  dexPrototype = callSite.getDynamicMethodPrototype();
               }

               IJavaType[] fullPrototype = JavaTypeUtil.parseFullPrototype(dexPrototype, this.E);
               IJavaType fi = fullPrototype[0];
               if (fi == this.E.kS || fi == this.E.wS || fi == this.E.UT) {
                  fi = this.E.fI;
               }

               IDVar pc2 = this.pC(n, i, fi, array, array2);
               if (pc2 != null && array2[0] == 0) {
                  dalvikInstruction = array[0];
               }

               boolean b = dInvokeType == DInvokeType.STATIC || dInvokeType == DInvokeType.LAMBDA;
               int l = 0;
               int n8 = 0;
               if (!b) {
                  l++;
                  n8++;
               }

               for (int n9 = 1; n9 < fullPrototype.length; n9++) {
                  if (fullPrototype[n9] == this.E.sY || fullPrototype[n9] == this.E.ld) {
                     l++;
                  }

                  l++;
                  n8++;
               }

               int[] array3;
               if (opcode != 110 && opcode != 111 && opcode != 112 && opcode != 113 && opcode != 114 && opcode != 250 && opcode != 252) {
                  long parameterValue = dalvikInstruction2.getParameterValue(n6);
                  int n12 = (int)(parameterValue & -1L);
                  int n13 = (int)(parameterValue >> 32 & -1L);
                  array3 = new int[n13 - n12 + 1];
                  int n14 = 0;
                  int n15 = n12;

                  while (n15 <= n13) {
                     array3[n14++] = n15++;
                  }
               } else {
                  array3 = new int[dalvikInstruction2.getParameterCount() - n6];
                  int n10 = 0;

                  for (int n11 = n6; n11 < dalvikInstruction2.getParameterCount(); n11++) {
                     array3[n10++] = (int)dalvikInstruction2.getParameterValue(n11);
                  }
               }

               if (l != array3.length) {
                  throw new DexDecConversionException(dalvikInstruction2, Strings.ff("Unexpected registers count: %d, %d", l, array3.length));
               }

               IDExpression[] array4 = new IDExpression[n8];
               int n16 = 0;
               int n17 = 0;
               if (!b) {
                  array4[n16++] = this.pC(array3[n17], this.E.parseType(classTypeSignature));
                  n17++;
               }

               for (int n18 = 1; n16 < array4.length; n18++) {
                  IJavaType fi2 = fullPrototype[n18];
                  if (fi2 == this.E.E || fi2 == this.E.UT || fi2 == this.E.wS || fi2 == this.E.kS || fi2 == this.E.A) {
                     fi2 = this.E.fI;
                  }

                  array4[n16++] = this.pC(array3[n17], fi2);
                  if (fi2 == this.E.sY || fi2 == this.E.ld) {
                     if (array3[n17 + 1] != array3[n17] + 1) {
                        throw new RuntimeException();
                     }

                     n17++;
                  }

                  n17++;
               }

               if (!b && method.getName(false).equals("<init>")) {
                  set.clear();
                  set2.clear();
                  IDalvikInstruction pc3 = null;
                  if (this.z) {
                     if (i >= 1) {
                        IDalvikInstruction dalvikInstruction3 = (IDalvikInstruction)value.get(i - 1);
                        IDInstruction idInstruction2;
                        if ((dalvikInstruction3.getOpcode() == 34 || dalvikInstruction3.getOpcode() == 1023)
                           && (int)dalvikInstruction3.getParameterValue(0) == array3[0]
                           && !list.isEmpty()
                           && (idInstruction2 = (IDInstruction)list.get(list.size() - 1)).isAssignToVar()
                           && idInstruction2.getAssignSource() instanceof IDAllocObjectInfo) {
                           pc3 = dalvikInstruction3;
                           idInstruction2.setAssignSource(this.UT.createNull());
                        }
                     }
                  } else {
                     pc3 = this.pC(dalvikInstruction2, array3[0], set, set2, value, i);
                  }

                  if (pc3 != null) {
                     int n19 = array3[0];
                     String signature = this.ys.getType((int)pc3.getParameterValue(1)).getSignature(false);
                     boolean b2 = false;
                     if (!signature.equals(classTypeSignature)) {
                        b2 = true;
                     }

                     this.hK.addAll(set2);
                     this.Ek.remove(pc3);
                     IJavaType pc4 = this.E.pC(signature);
                     IJavaType javaType = b2 ? this.E.pC(classTypeSignature) : pc4;
                     IDExpression[] array5 = new IDExpression[array4.length - 1];

                     for (int n20 = 1; n20 < array4.length; n20++) {
                        array5[n20 - 1] = array4[n20];
                     }

                     idInstruction = this.wS
                        .pC(
                           DOpcodeType.IR_ASSIGN,
                           this.pC(n19, pc4),
                           this.UT
                              .createNewInfo(
                                 pc4, javaType, this.UT.createIndex((int)dalvikInstruction2.getParameterValue(0)), array5, method.getSignature(false)
                              )
                        );
                     if (!set.isEmpty()) {
                        list2 = new ArrayList(set.size());

                        for (Object intValue : set) {
                           if ((Integer)intValue != n19) {
                              list2.add(this.wS.pC(DOpcodeType.IR_ASSIGN, this.pC((Integer)intValue, pc4), this.pC(n19, pc4)));
                           }
                        }
                     }
                  }
               }

               if (idInstruction == null) {
                  IDCallInfo callInfo = this.UT.createCallInfo(this.UT.createIndex(n4), array4, fi, method.getSignature(false), dInvokeType);
                  if (pc2 != null) {
                     idInstruction = this.wS.pC(DOpcodeType.IR_ASSIGN, pc2, callInfo);
                  } else {
                     idInstruction = this.wS.pC(DOpcodeType.IR_INVOKE, null, callInfo);
                  }
               }
               break;
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.wS(opcode)),
                     this.UT.createOperation(this.wS(opcode), null, this.kS(opcode), this.pC(n2, this.wS(opcode)))
                  );
               break;
            case 129:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.sY), this.UT.createOperation(this.E.sY, null, this.kS(opcode), this.pC(n2, this.E.fI)));
               break;
            case 130:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.ys), this.UT.createOperation(this.E.ys, null, this.kS(opcode), this.pC(n2, this.E.fI)));
               break;
            case 131:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.ld), this.UT.createOperation(this.E.ld, null, this.kS(opcode), this.pC(n2, this.E.fI)));
               break;
            case 132:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.E), this.UT.createOperation(this.E.E, null, this.kS(opcode), this.pC(n2, this.E.sY)));
               break;
            case 133:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.ys), this.UT.createOperation(this.E.ys, null, this.kS(opcode), this.pC(n2, this.E.sY)));
               break;
            case 134:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.ld), this.UT.createOperation(this.E.ld, null, this.kS(opcode), this.pC(n2, this.E.sY)));
               break;
            case 135:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.E), this.UT.createOperation(this.E.E, null, this.kS(opcode), this.pC(n2, this.E.ys)));
               break;
            case 136:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.sY), this.UT.createOperation(this.E.sY, null, this.kS(opcode), this.pC(n2, this.E.ys)));
               break;
            case 137:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.ld), this.UT.createOperation(this.E.ld, null, this.kS(opcode), this.pC(n2, this.E.ys)));
               break;
            case 138:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.E), this.UT.createOperation(this.E.E, null, this.kS(opcode), this.pC(n2, this.E.ld)));
               break;
            case 139:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.sY), this.UT.createOperation(this.E.sY, null, this.kS(opcode), this.pC(n2, this.E.ld)));
               break;
            case 140:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.ys), this.UT.createOperation(this.E.ys, null, this.kS(opcode), this.pC(n2, this.E.ld)));
               break;
            case 141:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.kS), this.UT.createOperation(this.E.kS, null, this.kS(opcode), this.pC(n2, this.E.fI)));
               break;
            case 142:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.wS), this.UT.createOperation(this.E.wS, null, this.kS(opcode), this.pC(n2, this.E.fI)));
               break;
            case 143:
               idInstruction = this.wS
                  .pC(DOpcodeType.IR_ASSIGN, this.pC(j, this.E.UT), this.UT.createOperation(this.E.UT, null, this.kS(opcode), this.pC(n2, this.E.fI)));
               break;
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.wS(opcode)),
                     this.UT.createOperation(this.wS(opcode), this.pC(n2, this.wS(opcode)), this.kS(opcode), this.pC(n3, this.wS(opcode)))
                  );
               break;
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.wS(opcode)),
                     this.UT.createOperation(this.wS(opcode), this.pC(n2, this.wS(opcode)), this.kS(opcode), this.pC(n3, this.wS(opcode)))
                  );
               break;
            case 163:
            case 164:
            case 165:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.wS(opcode)),
                     this.UT.createOperation(this.wS(opcode), this.pC(n2, this.wS(opcode)), this.kS(opcode), this.pC(n3, this.E.fI))
                  );
               break;
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.E.E),
                     this.UT.createOperation(this.E.E, this.pC(j, this.wS(opcode)), this.kS(opcode), this.pC(n2, this.wS(opcode)))
                  );
               break;
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.wS(opcode)),
                     this.UT.createOperation(this.wS(opcode), this.pC(j, this.wS(opcode)), this.kS(opcode), this.pC(n2, this.wS(opcode)))
                  );
               break;
            case 195:
            case 196:
            case 197:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.wS(opcode)),
                     this.UT.createOperation(this.wS(opcode), this.pC(j, this.wS(opcode)), this.kS(opcode), this.pC(n2, this.E.fI))
                  );
               break;
            case 208:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.E.fI),
                     this.UT
                        .createOperation(
                           this.E.fI, this.pC(n2, this.E.fI), this.kS(opcode), this.UT.createConstant((int)dalvikInstruction2.getParameterValue(2), this.E.fI)
                        )
                  );
               break;
            case 209:
            case 217:
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, this.E.fI),
                     this.UT
                        .createOperation(
                           this.E.fI, this.UT.createConstant((int)dalvikInstruction2.getParameterValue(2), this.E.fI), this.kS(opcode), this.pC(n2, this.E.fI)
                        )
                  );
               break;
            case 254:
               int n21 = (int)dalvikInstruction2.getParameterValue(1);
               IDexMethodHandle methodHandle2 = this.ys.getMethodHandle(n21);
               String s = "";
               if (methodHandle2.getMethodHandleType().isFieldAccessor()) {
                  s = "_`" + this.ys.getField(methodHandle2.getFieldOrMethodIndex()).getName(false);
               } else if (methodHandle2.getMethodHandleType().isMethodInvoker()) {
                  s = "_`" + this.ys.getMethod(methodHandle2.getFieldOrMethodIndex()).getName(false);
               }

               IDexMethod addMethod = this.ys.addMethod("Ljeb/synthetic/PooledMethodHandles;", "Entry" + n21 + s, "()Ljava/lang/invoke/MethodHandle;");
               IJavaType pc5 = this.E.pC("Ljava/lang/invoke/MethodHandle;");
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, pc5),
                     this.UT.createCallInfo(this.UT.createIndex(addMethod.getIndex()), new IDExpression[0], pc5, addMethod.getSignature(), DInvokeType.STATIC)
                  );
               break;
            case 255:
               IDexMethod addMethod2 = this.ys
                  .addMethod("Ljeb/synthetic/PooledMethodTypes;", "Entry" + (int)dalvikInstruction2.getParameterValue(1), "()Ljava/lang/invoke/MethodType;");
               IJavaType pc6 = this.E.pC("Ljava/lang/invoke/MethodType;");
               idInstruction = this.wS
                  .pC(
                     DOpcodeType.IR_ASSIGN,
                     this.pC(j, pc6),
                     this.UT
                        .createCallInfo(this.UT.createIndex(addMethod2.getIndex()), new IDExpression[0], pc6, addMethod2.getSignature(), DInvokeType.STATIC)
                  );
               break;
            default:
               pC.warn(
                  "%04Xh: Cannot convert Dalvik instruction \"%s\" (opcode: %Xh)", dalvikInstruction2.getOffset(), dalvikInstruction2.getMnemonic(), opcode
               );
               throw new DexDecConversionException(dalvikInstruction2);
         }

         int m = (int)dalvikInstruction2.getOffset();
         this.Ab.put(m, this.ah);
         this.rl.put(this.ah, m);
         this.NS.add(dalvikInstruction2.getOpcode());
         if (dalvikInstruction != null) {
            this.Ab.put((int)dalvikInstruction.getOffset(), this.ah);
            this.NS.add(dalvikInstruction.getOpcode());
            this.UO.add(dalvikInstruction.getOffset());
            dalvikInstruction = null;
         }

         int n44 = (int)dalvikInstruction2.getOffset();
         if (idInstruction != null) {
            idInstruction.setOffset(this.ah++);
            idInstruction.updateAllPhysicalOffsets(n44);
            list.add(idInstruction);
         }

         i++;
         if (list2 != null) {
            for (IDInstruction idInstruction3 : list2) {
               this.rl.put(this.ah, m);
               idInstruction3.setOffset(this.ah++);
               idInstruction3.updateAllPhysicalOffsets(n44);
               list.add(idInstruction3);
            }

            list2 = null;
         }
      }

      int n45 = (int)value.getEndAddress();
      this.Ab.put(n45, this.ah);
      this.rl.put(this.ah, n45);
      return list;
   }

   private IDVar pC(int index, int n, IJavaType javaType, IDalvikInstruction[] array, int[] array2) {
      array[0] = null;
      array2[0] = 0;
      BasicBlock basicBlock = this.oT.get(index);
      IDalvikInstruction dalvikInstruction = null;
      int opcode = 0;
      boolean b = false;

      while (array2[0] <= 10) {
         if (dalvikInstruction == null || (opcode = dalvikInstruction.getOpcode()) == 0) {
            if (++n >= basicBlock.size()) {
               n = 0;
               if (++index >= this.oT.size()) {
                  return null;
               }

               basicBlock = this.oT.get(index);
            }

            dalvikInstruction = (IDalvikInstruction)basicBlock.get(n);
         } else {
            if (opcode != 10 && opcode != 12 && opcode != 11) {
               if (opcode != 40 && opcode != 41 && opcode != 42) {
                  return null;
               }

               basicBlock = (BasicBlock<IDalvikInstruction>)this.WR
                  .get((long)((int)dalvikInstruction.getOffset() + 2 * (int)dalvikInstruction.getParameterValue(0)));
               if (basicBlock != null && !basicBlock.isEmpty()) {
                  index = this.oT.getBlocks().indexOf(basicBlock);
                  n = 0;
                  dalvikInstruction = (IDalvikInstruction)basicBlock.get(0);
                  int n2 = 0;
                  array2[0]++;
                  continue;
               }

               return null;
            }

            b = true;
            break;
         }
      }

      if (!b) {
         return null;
      } else {
         int n3;
         if (javaType == this.E.A || javaType == this.E.kS || javaType == this.E.wS || javaType == this.E.UT || javaType == this.E.E || javaType == this.E.ys) {
            n3 = 10;
         } else if (javaType == this.E.sY || javaType == this.E.ld) {
            n3 = 11;
         } else if (javaType.isObject()) {
            n3 = 12;
         } else {
            if (javaType != this.E.fI) {
               if (javaType == this.E.pC) {
                  pC.warn(S.L("Potential illegal move-result for void-returning invocation"));
                  return null;
               }

               throw new RuntimeException("Unexpected type to store a return value: " + javaType);
            }

            n3 = 10;
         }

         if (n3 != opcode) {
            throw new RuntimeException("Unexpected move-result-xxx opcode");
         } else {
            IDVar pc = this.pC((int)dalvikInstruction.getParameterValue(0), javaType);
            array[0] = dalvikInstruction;
            return pc;
         }
      }
   }

   private IDVar pC(int n, int n2, IJavaType javaType, IDalvikInstruction[] array) {
      BasicBlock value = this.oT.get(n);
      IDalvikInstruction dalvikInstruction = null;
      if (n2 + 1 < value.size()) {
         dalvikInstruction = (IDalvikInstruction)value.get(n2 + 1);
      } else if (n + 1 < this.oT.size()) {
         dalvikInstruction = (IDalvikInstruction)this.oT.get(n + 1).get(0);
      }

      if (dalvikInstruction == null) {
         return null;
      } else {
         int opcode = dalvikInstruction.getOpcode();
         if (opcode == 10 || opcode == 11) {
            throw new RuntimeException();
         } else if (opcode != 12) {
            return null;
         } else {
            IDVar pc = this.pC((int)dalvikInstruction.getParameterValue(0), javaType);
            array[0] = dalvikInstruction;
            return pc;
         }
      }
   }

   private IDalvikInstruction pC(IDalvikInstruction dalvikInstruction, int n, Set set, Set set2, BasicBlock basicBlock, int n2) {
      int i = n;
      Long checkSingleDef = this.fI.checkSingleDef(dalvikInstruction.getOffset(), n);
      if (checkSingleDef == null) {
         return null;
      } else {
         IDalvikInstruction dalvikInstruction2 = (IDalvikInstruction)this.oT.getInstruction(checkSingleDef);
         if (dalvikInstruction2 == null) {
            return null;
         } else {
            int opcode = dalvikInstruction2.getOpcode();
            if (opcode != 34 && opcode != 1023) {
               do {
                  Collection blockDefUses = this.fI.getBlockDefUses(dalvikInstruction2.getOffset(), i);
                  if (blockDefUses == null) {
                     return null;
                  }

                  int opcode2 = dalvikInstruction2.getOpcode();
                  if (opcode2 != 34 && opcode2 != 1023 && opcode2 != 7 && opcode2 != 8 && opcode2 != 9) {
                     return null;
                  }

                  Assert.a(dalvikInstruction2.getParameterValue(0) == i);
                  Iterator iterator = blockDefUses.iterator();

                  while (iterator.hasNext()) {
                     int indexOfInstruction = basicBlock.getIndexOfInstruction((Long)iterator.next());
                     if (indexOfInstruction < n2 && indexOfInstruction >= 0) {
                        IDalvikInstruction dalvikInstruction3 = (IDalvikInstruction)basicBlock.get(indexOfInstruction);
                        int opcode3 = dalvikInstruction3.getOpcode();
                        if (opcode3 != 7 && opcode3 != 8 && opcode3 != 9) {
                           return null;
                        }

                        int j = (int)dalvikInstruction3.getParameterValue(0);
                        if (j != n) {
                           set.add(j);
                        }

                        set2.add(dalvikInstruction3);
                     }
                  }

                  if (opcode2 == 34 || opcode2 == 1023) {
                     return dalvikInstruction2;
                  }

                  i = (int)dalvikInstruction2.getParameterValue(1);
                  set.add(i);
                  set2.add(dalvikInstruction2);
                  Long checkSingleDef2 = this.fI.checkSingleDef(dalvikInstruction2.getOffset(), i);
                  if (checkSingleDef2 == null) {
                     return null;
                  }

                  dalvikInstruction2 = (IDalvikInstruction)this.oT.getInstruction(checkSingleDef2);
               } while (dalvikInstruction2 != null);

               return null;
            } else {
               Assert.a(dalvikInstruction2.getParameterValue(0) == n);
               return dalvikInstruction2;
            }
         }
      }
   }

   private IJavaOperator kS(int n) {
      switch (n) {
         case 32:
            return this.sY.eP;
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 115:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         default:
            throw new RuntimeException();
         case 50:
         case 56:
            return this.sY.UO;
         case 51:
         case 57:
            return this.sY.Ab;
         case 52:
         case 58:
            return this.sY.rl;
         case 53:
         case 59:
            return this.sY.z;
         case 54:
         case 60:
            return this.sY.Ek;
         case 55:
         case 61:
            return this.sY.hK;
         case 123:
         case 125:
         case 127:
         case 128:
            return this.sY.NS;
         case 124:
         case 126:
            return this.sY.vP;
         case 129:
            return this.sY.LM;
         case 130:
         case 131:
         case 133:
         case 134:
         case 135:
         case 136:
         case 138:
         case 139:
            return this.sY.UW;
         case 132:
            return this.sY.EX;
         case 137:
            return this.sY.sO;
         case 140:
            return this.sY.mv;
         case 141:
            return this.sY.Er;
         case 142:
            return this.sY.FE;
         case 143:
            return this.sY.Aj;
         case 144:
         case 155:
         case 166:
         case 171:
         case 176:
         case 187:
         case 198:
         case 203:
         case 208:
         case 216:
            return this.sY.kS;
         case 145:
         case 156:
         case 167:
         case 172:
         case 177:
         case 188:
         case 199:
         case 204:
         case 209:
         case 217:
            return this.sY.wS;
         case 146:
         case 157:
         case 168:
         case 173:
         case 178:
         case 189:
         case 200:
         case 205:
         case 210:
         case 218:
            return this.sY.UT;
         case 147:
         case 158:
         case 169:
         case 174:
         case 179:
         case 190:
         case 201:
         case 206:
         case 211:
         case 219:
            return this.sY.E;
         case 148:
         case 159:
         case 170:
         case 175:
         case 180:
         case 191:
         case 202:
         case 207:
         case 212:
         case 220:
            return this.sY.sY;
         case 149:
         case 160:
         case 181:
         case 192:
         case 213:
         case 221:
            return this.sY.ys;
         case 150:
         case 161:
         case 182:
         case 193:
         case 214:
         case 222:
            return this.sY.ld;
         case 151:
         case 162:
         case 183:
         case 194:
         case 215:
         case 223:
            return this.sY.gp;
         case 152:
         case 163:
         case 184:
         case 195:
         case 224:
            return this.sY.oT;
         case 153:
         case 164:
         case 185:
         case 196:
         case 225:
            return this.sY.fI;
         case 154:
         case 165:
         case 186:
         case 197:
         case 226:
            return this.sY.WR;
      }
   }

   private IJavaType wS(int n) {
      switch (n) {
         case 45:
         case 46:
            return this.E.ys;
         case 47:
         case 48:
            return this.E.ld;
         case 49:
            return this.E.sY;
         case 68:
         case 75:
         case 82:
         case 89:
         case 96:
         case 103:
         case 1791:
         case 3583:
         case 5375:
         case 7167:
            return this.E.gp;
         case 69:
         case 76:
         case 83:
         case 90:
         case 97:
         case 104:
         case 2047:
         case 3839:
         case 5631:
         case 7423:
            return this.E.oT;
         case 70:
         case 77:
         case 84:
         case 91:
         case 98:
         case 105:
         case 2303:
         case 4095:
         case 5887:
         case 7679:
            return this.E.WR;
         case 71:
         case 78:
         case 85:
         case 92:
         case 99:
         case 106:
         case 2559:
         case 4351:
         case 6143:
         case 7935:
            return this.E.A;
         case 72:
         case 79:
         case 86:
         case 93:
         case 100:
         case 107:
         case 2815:
         case 4607:
         case 6399:
         case 8191:
            return this.E.kS;
         case 73:
         case 80:
         case 87:
         case 94:
         case 101:
         case 108:
         case 3071:
         case 4863:
         case 6655:
         case 8447:
            return this.E.wS;
         case 74:
         case 81:
         case 88:
         case 95:
         case 102:
         case 109:
         case 3327:
         case 5119:
         case 6911:
         case 8703:
            return this.E.UT;
         case 123:
         case 124:
            return this.E.fI;
         case 125:
         case 126:
            return this.E.sY;
         case 127:
            return this.E.ys;
         case 128:
            return this.E.ld;
         case 144:
         case 145:
         case 146:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
            return this.E.fI;
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         case 160:
         case 161:
         case 162:
         case 163:
         case 164:
         case 165:
            return this.E.sY;
         case 166:
         case 167:
         case 168:
         case 169:
         case 170:
            return this.E.ys;
         case 171:
         case 172:
         case 173:
         case 174:
         case 175:
            return this.E.ld;
         case 176:
         case 177:
         case 178:
         case 179:
         case 180:
         case 181:
         case 182:
         case 183:
         case 184:
         case 185:
         case 186:
            return this.E.fI;
         case 187:
         case 188:
         case 189:
         case 190:
         case 191:
         case 192:
         case 193:
         case 194:
         case 195:
         case 196:
         case 197:
            return this.E.sY;
         case 198:
         case 199:
         case 200:
         case 201:
         case 202:
            return this.E.ys;
         case 203:
         case 204:
         case 205:
         case 206:
         case 207:
            return this.E.ld;
         default:
            throw new RuntimeException();
      }
   }

   public static boolean pC(int i) {
      return UW.contains(i);
   }

   static {
      (UW = new HashSet()).add(116);
      UW.add(117);
      UW.add(118);
      UW.add(119);
      UW.add(120);
      UW.add(251);
      UW.add(123);
      UW.add(124);
      UW.add(127);
      UW.add(125);
      UW.add(126);
      UW.add(128);
      UW.add(129);
      UW.add(130);
      UW.add(131);
      UW.add(132);
      UW.add(133);
      UW.add(134);
      UW.add(135);
      UW.add(136);
      UW.add(137);
      UW.add(138);
      UW.add(139);
      UW.add(140);
      UW.add(141);
      UW.add(142);
      UW.add(143);
      UW.add(144);
      UW.add(145);
      UW.add(146);
      UW.add(147);
      UW.add(148);
      UW.add(149);
      UW.add(150);
      UW.add(151);
      UW.add(152);
      UW.add(153);
      UW.add(154);
      UW.add(166);
      UW.add(167);
      UW.add(168);
      UW.add(169);
      UW.add(170);
      UW.add(155);
      UW.add(156);
      UW.add(157);
      UW.add(158);
      UW.add(159);
      UW.add(160);
      UW.add(161);
      UW.add(162);
      UW.add(171);
      UW.add(172);
      UW.add(173);
      UW.add(174);
      UW.add(175);
      UW.add(163);
      UW.add(164);
      UW.add(165);
      UW.add(176);
      UW.add(177);
      UW.add(178);
      UW.add(179);
      UW.add(180);
      UW.add(181);
      UW.add(182);
      UW.add(183);
      UW.add(184);
      UW.add(185);
      UW.add(186);
      UW.add(198);
      UW.add(199);
      UW.add(200);
      UW.add(201);
      UW.add(202);
      UW.add(187);
      UW.add(188);
      UW.add(189);
      UW.add(190);
      UW.add(191);
      UW.add(192);
      UW.add(193);
      UW.add(194);
      UW.add(203);
      UW.add(204);
      UW.add(205);
      UW.add(206);
      UW.add(207);
      UW.add(195);
      UW.add(196);
      UW.add(197);
      UW.add(208);
      UW.add(210);
      UW.add(211);
      UW.add(212);
      UW.add(213);
      UW.add(214);
      UW.add(215);
      UW.add(216);
      UW.add(218);
      UW.add(219);
      UW.add(220);
      UW.add(221);
      UW.add(222);
      UW.add(223);
      UW.add(224);
      UW.add(225);
      UW.add(226);
      UW.add(209);
      UW.add(217);
   }

   private class Av {
      List pC = new ArrayList();

      public Av() {
      }

      public IDTarget pC(int n) {
         IDTarget target = bqp.this.UT.createTarget(n);
         this.pC.add(target);
         return target;
      }

      public List pC() {
         return this.pC;
      }
   }

   private class Sv {
      static int pC = 30;
      private IJavaType kS;
      private Set wS = new HashSet();
      private int UT;

      IJavaType pC(IDalvikInstruction dalvikInstruction) {
         return this.pC(dalvikInstruction, (int)dalvikInstruction.getParameterValue(0)) <= 0 ? null : this.kS;
      }

      private int pC(IDalvikInstruction dalvikInstruction, int n) {
         if (!this.wS.add(dalvikInstruction.getOffset())) {
            return 0;
         } else {
            for (long longValue : bqp.this.fI.getUseDefs(dalvikInstruction.getOffset(), n)) {
               if (longValue >= 0L) {
                  IDalvikInstruction dalvikInstruction2 = (IDalvikInstruction)bqp.this.oT.getInstruction(longValue);
                  if (dalvikInstruction2 != null) {
                     int opcode = dalvikInstruction2.getOpcode();
                     if (opcode != 7 && opcode != 9 && opcode != 8) {
                        if (opcode == 35 || opcode == 1279) {
                           if ((int)dalvikInstruction2.getParameterValue(0) != n) {
                              return -2;
                           } else {
                              IJavaType type = bqp.this.E.parseType(bqp.this.ys.getType((int)dalvikInstruction2.getParameterValue(2)).getSignature(false));
                              if (type.getDimensions() <= 0) {
                                 throw new RuntimeException("Expected an array type for new-array");
                              } else {
                                 this.kS = type;
                                 return 1;
                              }
                           }
                        }

                        if (opcode == 12) {
                           Couple instructionLocation = bqp.this.oT.getInstructionLocation(dalvikInstruction2.getOffset());
                           if (instructionLocation != null) {
                              BasicBlock basicBlock = (BasicBlock)instructionLocation.getFirst();
                              int intValue = (Integer)instructionLocation.getSecond();
                              if (intValue >= 1) {
                                 IDalvikInstruction dalvikInstruction3 = (IDalvikInstruction)basicBlock.get(intValue - 1);
                                 switch (dalvikInstruction3.getOpcode()) {
                                    case 110:
                                    case 111:
                                    case 112:
                                    case 113:
                                    case 114:
                                    case 116:
                                    case 117:
                                    case 118:
                                    case 119:
                                    case 120:
                                       IDexType returnType = bqp.this.ys.getMethod((int)dalvikInstruction3.getParameterValue(0)).getReturnType();
                                       if (returnType.isArray()) {
                                          IJavaType type2 = bqp.this.E.parseType(returnType.getSignature(false));
                                          if (type2.getDimensions() <= 0) {
                                             throw new RuntimeException("Expected an array type returned by invoke-xxx");
                                          }

                                          this.kS = type2;
                                          return 1;
                                       }
                                    case 115:
                                 }
                              }
                           }
                        }
                     } else {
                        if ((int)dalvikInstruction2.getParameterValue(0) != n) {
                           return -2;
                        }

                        int pc = this.pC(dalvikInstruction2, (int)dalvikInstruction2.getParameterValue(1));
                        if (pc != 0) {
                           return pc;
                        }
                     }
                  }
               }
            }

            return 0;
         }
      }
   }
}
