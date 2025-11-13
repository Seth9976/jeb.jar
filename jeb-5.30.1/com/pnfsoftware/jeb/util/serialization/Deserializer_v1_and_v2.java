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

class Deserializer_v1_and_v2 extends AbstractInternalDeserializer {
   private static final ILogger logger = GlobalLog.getLogger(Deserializer_v1_and_v2.class);
   private int flagMask;

   public Deserializer_v1_and_v2(boolean var1, ITypeIdProvider var2, List var3, LEDataInputStream var4) {
      super(var2, var3, var4);
      this.flagMask = var1 ? -65408 : -65344;
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
            var3 = this.in.readInt();
            if ((var3 & this.flagMask) != 0) {
               throw new SerializationException(Strings.ff("Unsupported flag in serialized object: 0x%X", var3), var2);
            }

            if (var3 == 1) {
               return null;
            }

            var4 = this.in.readInt();
            if ((var3 & 4) != 0) {
               Object var26 = this.objmap.get(var4);
               if (var26 == null) {
                  throw new SerializationException(Strings.ff("Expected an already-deserialized object for id: %d", var4), var2);
               }

               if (var1 != null) {
                  throw new SerializationException("An empty object was provided, but an already-deserialized object was found", var2);
               }

               return var26;
            }

            var9 = var3 >> 8 & 0xFF;
            var6 = this.in.readInt();
            if ((var3 & 16) != 0) {
               int var10 = this.in.readInt();
               byte[] var11 = new byte[var10];
               this.in.readFully(var11);
               var5 = Strings.decodeUTF8(var11);
            } else if ((var3 & 64) != 0) {
               int var24 = this.in.readInt();
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
            int var28 = this.in.readInt();
            var8 = var2.getEnumConstants()[var28];
            this.objmap.put(var4, var8);
            return var8;
         } else {
            int var27 = this.in.readInt();
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
               int[] var30 = new int[var9];
               int var12 = this.in.readInt();
               var30[0] = var12;
               Object var13 = Array.newInstance(var2, var30);
               this.registerObject(var7, var4, var13);

               for (int var14 = 0; var14 < var12; var14++) {
                  Array.set(var13, var14, this.read());
               }

               var8 = var13;
            } else if (var2 == Object.class) {
               if (var1 == null) {
                  throw new SerializationException("Unexpected null object", var2);
               }

               var8 = var1;
            } else if (var2 == Boolean.class) {
               var8 = this.in.readByte() == 0 ? Boolean.FALSE : Boolean.TRUE;
            } else if (var2 == boolean[].class) {
               int var31 = this.in.readInt();
               boolean[] var46 = new boolean[var31];

               for (int var61 = 0; var61 < var31; var61++) {
                  var46[var61] = this.in.readByte() != 0;
               }

               var8 = var46;
            } else if (var2 == Byte.class) {
               var8 = this.in.readByte();
            } else if (var2 == byte[].class) {
               int var32 = this.in.readInt();
               byte[] var47 = new byte[var32];

               for (int var62 = 0; var62 < var32; var62++) {
                  var47[var62] = this.in.readByte();
               }

               var8 = var47;
            } else if (var2 == Character.class) {
               var8 = this.in.readChar();
            } else if (var2 == char[].class) {
               int var33 = this.in.readInt();
               char[] var48 = new char[var33];

               for (int var63 = 0; var63 < var33; var63++) {
                  var48[var63] = this.in.readChar();
               }

               var8 = var48;
            } else if (var2 == Short.class) {
               var8 = this.in.readShort();
            } else if (var2 == short[].class) {
               int var34 = this.in.readInt();
               short[] var49 = new short[var34];

               for (int var64 = 0; var64 < var34; var64++) {
                  var49[var64] = this.in.readShort();
               }

               var8 = var49;
            } else if (var2 == Integer.class) {
               var8 = this.in.readInt();
            } else if (var2 == int[].class) {
               int var35 = this.in.readInt();
               int[] var50 = new int[var35];

               for (int var65 = 0; var65 < var35; var65++) {
                  var50[var65] = this.in.readInt();
               }

               var8 = var50;
            } else if (var2 == Long.class) {
               var8 = this.in.readLong();
            } else if (var2 == long[].class) {
               int var36 = this.in.readInt();
               long[] var51 = new long[var36];

               for (int var66 = 0; var66 < var36; var66++) {
                  var51[var66] = this.in.readLong();
               }

               var8 = var51;
            } else if (var2 == Float.class) {
               var8 = this.in.readFloat();
            } else if (var2 == float[].class) {
               int var37 = this.in.readInt();
               float[] var52 = new float[var37];

               for (int var67 = 0; var67 < var37; var67++) {
                  var52[var67] = this.in.readFloat();
               }

               var8 = var52;
            } else if (var2 == Double.class) {
               var8 = this.in.readDouble();
            } else if (var2 == double[].class) {
               int var38 = this.in.readInt();
               double[] var53 = new double[var38];

               for (int var68 = 0; var68 < var38; var68++) {
                  var53[var68] = this.in.readDouble();
               }

               var8 = var53;
            } else if (var2 == String.class) {
               int var39 = this.in.readInt();
               this.in.readInt();
               byte[] var54 = new byte[var39];
               this.in.readFully(var54);
               var8 = Strings.decodeUTF8(var54);
            } else if (var2 == BigInteger.class) {
               int var40 = this.in.readInt();
               byte[] var55 = new byte[var40];
               this.in.readFully(var55);
               var8 = new BigInteger(var55);
            } else if (var2 == BigDecimal.class) {
               int var41 = this.in.readInt();
               byte[] var56 = new byte[var41];
               this.in.readFully(var56);
               var8 = new BigDecimal(Strings.decodeUTF8(var56));
            } else if (var2 != ArrayList.class
               && var2 != LinkedList.class
               && var2 != ArrayDeque.class
               && var2 != HashSet.class
               && var2 != TreeSet.class
               && var2 != LinkedHashSet.class) {
               if (var2 != HashMap.class && var2 != TreeMap.class && var2 != LinkedHashMap.class && var2 != IdentityHashMap.class) {
                  throw new SerializationException("Unsupported type", var2);
               }

               int var43 = this.in.readInt();
               Object var58;
               if (var7) {
                  if (var2 == HashMap.class) {
                     var58 = new HashMap(var43);
                  } else if (var2 == TreeMap.class) {
                     var58 = new TreeMap();
                  } else if (var2 == LinkedHashMap.class) {
                     var58 = new LinkedHashMap(var43);
                  } else {
                     if (var2 != IdentityHashMap.class) {
                        throw new SerializationException("Unsupported map type", var2);
                     }

                     var58 = new IdentityHashMap(var43);
                  }
               } else {
                  var58 = (Map)var1;
               }

               this.registerObject(var7, var4, var58);
               PreMap var70 = new PreMap((Map)var58, var43);

               for (int var72 = 0; var72 < var43; var72++) {
                  Object var73 = this.read();
                  var70.recordObj(var73);
                  Object var16 = this.read();
                  var70.recordObj(var16);
               }

               this.deferredObjects.add(var70);
               var8 = var58;
            } else {
               int var42 = this.in.readInt();
               Object var57;
               if (var7) {
                  if (var2 == ArrayList.class) {
                     var57 = new ArrayList(var42);
                  } else if (var2 == LinkedList.class) {
                     var57 = new LinkedList();
                  } else if (var2 == ArrayDeque.class) {
                     var57 = new ArrayDeque(var42);
                  } else if (var2 == HashSet.class) {
                     var57 = new HashSet(var42);
                  } else if (var2 == TreeSet.class) {
                     var57 = new TreeSet();
                  } else {
                     if (var2 != LinkedHashSet.class) {
                        throw new SerializationException("Unsupported collection type", var2);
                     }

                     var57 = new LinkedHashSet();
                  }
               } else {
                  var57 = (Collection)var1;
               }

               this.registerObject(var7, var4, var57);
               PreCollection var69 = new PreCollection((Collection)var57, var42);

               for (int var71 = 0; var71 < var42; var71++) {
                  Object var15 = this.read();
                  var69.recordObj(var15);
               }

               this.deferredObjects.add(var69);
               var8 = var57;
            }

            this.registerObject(var7, var4, var8);
            return var8;
         }
      }
   }

   @Override
   public void restoreFields(Object var1, Class var2) throws IOException {
      while (true) {
         int var3 = this.in.readInt();
         if (var3 == 0) {
            return;
         }

         Object var4 = this.read();
         this.restoreFieldValue(var1, var2, var3, var4);
      }
   }
}
