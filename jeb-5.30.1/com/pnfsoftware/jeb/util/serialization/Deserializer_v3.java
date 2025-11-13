package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.SerConstructor;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

class Deserializer_v3 extends AbstractInternalDeserializer {
   private static final ILogger logger = GlobalLog.getLogger(Deserializer_v3.class);
   private int flagMask = -65408;

   public Deserializer_v3(ITypeIdProvider var1, List var2, LEDataInputStream var3) {
      super(var1, var2, var3);
   }

   @Override
   protected Object readInternal(Object var1, Class var2) throws IOException {
      int var3 = 0;
      int var4 = 0;
      String var5 = null;
      boolean var7 = var2 == null;
      if (var7 && var1 != null) {
         throw new SerializationException("Object should not be provided, a leaf is being deserialized", var2);
      } else {
         Object var8 = var1;
         int var9 = 0;
         int var6;
         if (var7) {
            var3 = this.in.readIntULEB128();
            if ((var3 & this.flagMask) != 0) {
               throw new SerializationException(Strings.ff("Unsupported flag in serialized object: 0x%X", var3), var2);
            }

            if (var3 == 1) {
               return null;
            }

            var4 = this.in.readIntULEB128();
            if ((var3 & 4) != 0) {
               Object var26 = this.objmap.get(var4);
               if (var26 == null) {
                  throw new SerializationException(Strings.ff("Expected an already-deserialized object for id: %d", var4), var2);
               }

               return var26;
            }

            var9 = var3 >> 8 & 0xFF;
            var6 = this.in.readIntULEB128() - 128;
            if ((var3 & 16) != 0) {
               int var10 = this.in.readIntULEB128();
               byte[] var11 = new byte[var10];
               this.in.readFully(var11);
               var5 = Strings.decodeUTF8(var11);
            } else if ((var3 & 64) != 0) {
               int var24 = this.in.readIntULEB128();
               Class var29 = this.objmap.get(var24).getClass();
               var5 = ReflectionHelper.reduceDimensions(var29).getName();
            }

            Class var25;
            try {
               var25 = var6 > 0 ? this.customTypeIdProvider.getType(var6) : (var6 < 0 ? this.nativeTypeIdProvider.getType(var6) : this.loadClass(var5));
            } catch (ClassNotFoundException var22) {
               throw new SerializationException(Strings.ff("Class not found for type id: %d", var6), var22, var2);
            }

            if (var2 != null && var2 != var25) {
               throw new SerializationException(Strings.ff("Unexpected class was found for type id %d: %s", var6, var25), var2);
            }

            var2 = var25;
            if (var25 == null) {
               throw new SerializationException(Strings.ff("Type was expected for id %d, got null", var6), var25);
            }
         } else {
            var6 = this.nativeTypeIdProvider.getId(var2);
            if (var6 == 0) {
               var6 = this.customTypeIdProvider.getId(var2);
            }
         }

         if ((var3 & 32) != 0) {
            int var28 = this.in.readIntULEB128();
            var8 = var2.getEnumConstants()[var28];
            this.objmap.put(var4, var8);
            return var8;
         } else {
            int var27 = this.in.readIntULEB128();
            if (var6 >= 0 && var9 == 0) {
               if (var7) {
                  Object var44 = this.constructorMap.get(var2);
                  if (var44 == null) {
                     var44 = this.mootObject;

                     try {
                        Constructor var59 = var2.getDeclaredConstructor();
                        if (var59.getAnnotation(SerConstructor.class) != null) {
                           var44 = var59;
                        }
                     } catch (NoSuchMethodException var20) {
                     } catch (Exception var21) {
                        throw new SerializationException(var21, var2);
                     }

                     this.constructorMap.put(var2, var44);
                  }

                  try {
                     if (var44 instanceof Constructor) {
                        var8 = ReflectionHelper.newInstance2((Constructor)var44);
                     } else {
                        var8 = this.objenesis.getInstantiatorOf(var2).newInstance();
                     }
                  } catch (Exception var19) {
                     throw new SerializationException(var19, var2);
                  }

                  this.registerObject(var7, var4, var8);
               }

               Class var45 = var2.getSuperclass();
               if (var45 != null) {
                  this.read(var8, var45);
               }

               AbstractInternalDeserializer.CustomMethods var60 = this.getCustomMethods(var8, var2);
               if (var60.mCustomRead != null) {
                  try {
                     ReflectionHelper.invoke2(var60.mCustomRead, var8, new DeserializerHelper(this, var8, var2, var27));
                  } catch (Exception var18) {
                     throw new SerializationException("Custom read failed", var18, var2);
                  }
               } else {
                  this.restoreFields(var8, var2);
               }

               if (var60.mCustomInit != null) {
                  try {
                     ReflectionHelper.invoke2(var60.mCustomInit, var8);
                  } catch (Exception var17) {
                     throw new SerializationException("Initializer failed", var17, var2);
                  }
               }

               this.notifyDeserializationEvent(1, var2, var8);
            } else if (var9 > 0) {
               int[] var43 = new int[var9];
               int var58 = this.in.readIntULEB128();
               var43[0] = var58;
               Object var70 = Array.newInstance(var2, var43);
               this.registerObject(var7, var4, var70);

               for (int var72 = 0; var72 < var58; var72++) {
                  Array.set(var70, var72, this.read());
               }

               var8 = var70;
            } else if (var2 == Object.class) {
               if (var1 == null) {
                  var1 = new Object();
               }

               var8 = var1;
            } else if (var2 == Boolean.class) {
               var8 = this.in.readByte() == 0 ? Boolean.FALSE : Boolean.TRUE;
            } else if (var2 == boolean[].class) {
               int var42 = this.in.readIntULEB128();
               boolean[] var57 = new boolean[var42];

               for (int var69 = 0; var69 < var42; var69++) {
                  var57[var69] = this.in.readByte() != 0;
               }

               var8 = var57;
            } else if (var2 == Byte.class) {
               var8 = this.in.readByte();
            } else if (var2 == byte[].class) {
               int var41 = this.in.readIntULEB128();
               byte[] var56 = new byte[var41];

               for (int var68 = 0; var68 < var41; var68++) {
                  var56[var68] = this.in.readByte();
               }

               var8 = var56;
            } else if (var2 == Character.class) {
               var8 = this.in.readChar();
            } else if (var2 == char[].class) {
               int var40 = this.in.readIntULEB128();
               char[] var55 = new char[var40];

               for (int var67 = 0; var67 < var40; var67++) {
                  var55[var67] = this.in.readChar();
               }

               var8 = var55;
            } else if (var2 == Short.class) {
               var8 = this.in.readShort();
            } else if (var2 == short[].class) {
               int var39 = this.in.readIntULEB128();
               short[] var54 = new short[var39];

               for (int var66 = 0; var66 < var39; var66++) {
                  var54[var66] = this.in.readShort();
               }

               var8 = var54;
            } else if (var2 == Integer.class) {
               var8 = this.in.readIntULEB128();
            } else if (var2 == int[].class) {
               int var38 = this.in.readIntULEB128();
               int[] var53 = new int[var38];

               for (int var65 = 0; var65 < var38; var65++) {
                  var53[var65] = this.in.readIntULEB128();
               }

               var8 = var53;
            } else if (var2 == Long.class) {
               var8 = this.in.readLongULEB128();
            } else if (var2 == long[].class) {
               int var37 = this.in.readIntULEB128();
               long[] var52 = new long[var37];

               for (int var64 = 0; var64 < var37; var64++) {
                  var52[var64] = this.in.readLongULEB128();
               }

               var8 = var52;
            } else if (var2 == Float.class) {
               var8 = this.in.readFloat();
            } else if (var2 == float[].class) {
               int var36 = this.in.readIntULEB128();
               float[] var51 = new float[var36];

               for (int var63 = 0; var63 < var36; var63++) {
                  var51[var63] = this.in.readFloat();
               }

               var8 = var51;
            } else if (var2 == Double.class) {
               var8 = this.in.readDouble();
            } else if (var2 == double[].class) {
               int var35 = this.in.readIntULEB128();
               double[] var50 = new double[var35];

               for (int var62 = 0; var62 < var35; var62++) {
                  var50[var62] = this.in.readDouble();
               }

               var8 = var50;
            } else if (var2 == String.class) {
               int var34 = this.in.readIntULEB128();
               byte[] var49 = new byte[var34];
               this.in.readFully(var49);
               var8 = Strings.decodeUTF8(var49);
            } else if (var2 == BigInteger.class) {
               int var33 = this.in.readIntULEB128();
               byte[] var48 = new byte[var33];
               this.in.readFully(var48);
               var8 = new BigInteger(var48);
            } else if (var2 == BigDecimal.class) {
               int var32 = this.in.readIntULEB128();
               byte[] var47 = new byte[var32];
               this.in.readFully(var47);
               var8 = new BigDecimal(Strings.decodeUTF8(var47));
            } else if (var2 == AtomicBoolean.class) {
               var8 = new AtomicBoolean(this.in.readByte() != 0);
            } else if (var2 == AtomicInteger.class) {
               var8 = new AtomicInteger(this.in.readIntULEB128());
            } else if (var2 == AtomicLong.class) {
               var8 = new AtomicLong(this.in.readLongULEB128());
            } else if (!Collection.class.isAssignableFrom(var2)
               || var2 != ArrayList.class
                  && var2 != LinkedList.class
                  && var2 != ArrayDeque.class
                  && var2 != HashSet.class
                  && var2 != TreeSet.class
                  && var2 != LinkedHashSet.class
                  && var2 != CopyOnWriteArrayList.class
                  && var2 != CopyOnWriteArraySet.class
                  && var2 != ConcurrentSkipListSet.class) {
               if (!Map.class.isAssignableFrom(var2)
                  || var2 != HashMap.class
                     && var2 != TreeMap.class
                     && var2 != LinkedHashMap.class
                     && var2 != IdentityHashMap.class
                     && var2 != ConcurrentHashMap.class
                     && var2 != ConcurrentSkipListMap.class) {
                  throw new SerializationException("Unsupported type", var2);
               }

               int var31 = this.in.readIntULEB128();
               Object var46;
               if (var7) {
                  if (var2 == HashMap.class) {
                     var46 = new HashMap(var31);
                  } else if (var2 == TreeMap.class) {
                     var46 = new TreeMap();
                  } else if (var2 == LinkedHashMap.class) {
                     var46 = new LinkedHashMap(var31);
                  } else if (var2 == IdentityHashMap.class) {
                     var46 = new IdentityHashMap(var31);
                  } else if (var2 == ConcurrentHashMap.class) {
                     var46 = new ConcurrentHashMap(var31);
                  } else {
                     if (var2 != ConcurrentSkipListMap.class) {
                        throw new SerializationException("Unsupported map type", var2);
                     }

                     var46 = new ConcurrentSkipListMap();
                  }
               } else {
                  var46 = (Map)var1;
               }

               this.registerObject(var7, var4, var46);
               PreMap var61 = new PreMap((Map)var46, var31);

               for (int var71 = 0; var71 < var31; var71++) {
                  Object var73 = this.read();
                  var61.recordObj(var73);
                  Object var16 = this.read();
                  var61.recordObj(var16);
               }

               this.deferredObjects.add(var61);
               var8 = var46;
            } else {
               int var30 = this.in.readIntULEB128();
               Object var12;
               if (var7) {
                  if (var2 == ArrayList.class) {
                     var12 = new ArrayList(var30);
                  } else if (var2 == LinkedList.class) {
                     var12 = new LinkedList();
                  } else if (var2 == ArrayDeque.class) {
                     var12 = new ArrayDeque(var30);
                  } else if (var2 == HashSet.class) {
                     var12 = new HashSet(var30);
                  } else if (var2 == TreeSet.class) {
                     var12 = new TreeSet();
                  } else if (var2 == LinkedHashSet.class) {
                     var12 = new LinkedHashSet();
                  } else if (var2 == CopyOnWriteArrayList.class) {
                     var12 = new CopyOnWriteArrayList();
                  } else if (var2 == CopyOnWriteArraySet.class) {
                     var12 = new CopyOnWriteArraySet();
                  } else {
                     if (var2 != ConcurrentSkipListSet.class) {
                        throw new SerializationException("Unsupported collection type", var2);
                     }

                     var12 = new ConcurrentSkipListSet();
                  }
               } else {
                  var12 = (Collection)var1;
               }

               this.registerObject(var7, var4, var12);
               PreCollection var13 = new PreCollection((Collection)var12, var30);

               for (int var14 = 0; var14 < var30; var14++) {
                  Object var15 = this.read();
                  var13.recordObj(var15);
               }

               this.deferredObjects.add(var13);
               var8 = var12;
            }

            this.registerObject(var7, var4, var8);
            return var8;
         }
      }
   }

   @Override
   public void restoreFields(Object var1, Class var2) throws IOException {
      while (true) {
         int var3 = this.in.readIntULEB128();
         if (var3 == 0) {
            return;
         }

         Object var4 = this.read();
         this.restoreFieldValue(var1, var2, var3, var4);
      }
   }
}
