package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.base.DynamicEnum;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.LEDataOutputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.reflect.ReflectionHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Serializer_v4 implements IInternalSerializer {
   private static final ILogger logger = GlobalLog.getLogger(Serializer.class);
   private static final int VERSION = 4;
   private LEDataOutputStream out;
   private ITypeIdProvider nativeTypeIdProvider;
   private ITypeIdProvider customTypeIdProvider;
   private IdentityHashMap objmap = new IdentityHashMap();
   private LinkedHashMap stringpool;
   private Map t2omap = new HashMap();
   private int currentObjectId = 1;
   private int rootCount;
   private boolean cancelled;
   private int depth;
   private int maxDepthReached;
   private int maxDepthAllowed = Integer.MAX_VALUE;
   private static Map fieldCache = new HashMap();
   private static Map fieldIdCache = new HashMap();
   private Set impTransientFields = new HashSet();
   private static Map customWriters = new HashMap();
   private static Map versionFields = new HashMap();

   public Serializer_v4(ITypeIdProvider customTypeIdProvider, OutputStream out, boolean b) {
      this.out = new LEDataOutputStream(new BufferedOutputStream(out));
      this.stringpool = b ? new LinkedHashMap() : null;
      this.nativeTypeIdProvider = NativeTypeIdProvider.getInstance();
      this.customTypeIdProvider = customTypeIdProvider;
   }

   void writeHeader() throws IOException {
      int n = 0;
      if (this.stringpool != null) {
         n |= 1;
      }

      this.out.write("PNF-ORPD".getBytes("UTF-8"));
      this.out.writeByte(4);
      this.out.writeByte(n);
      this.out.writeByte(0);
      this.out.writeByte(0);
      this.out.writeInt(0);
   }

   @Override
   public OutputStream getStream() {
      return new OutputStream() {
         @Override
         public void write(int b) throws IOException {
            Serializer_v4.this.out.write(b);
         }

         @Override
         public void write(byte[] b) throws IOException {
            Serializer_v4.this.out.write(b);
         }

         @Override
         public void write(byte[] array, int n, int n2) throws IOException {
            Serializer_v4.this.out.write(array, n, n2);
         }
      };
   }

   public int getObjectCount() {
      return this.objmap.size();
   }

   public int getMaxDepthReached() {
      return this.maxDepthReached;
   }

   public List getStringPool() {
      if (this.stringpool == null) {
         throw new IllegalStateException("This serializer was not configured to generate a string pool");
      } else {
         return new ArrayList(this.stringpool.keySet());
      }
   }

   public void serialize(Object o) throws IOException {
      if (this.rootCount == 0) {
         this.writeHeader();
      }

      this.write(o);
      if (this.cancelled) {
         throw new SerializationException(Strings.ff("The serialization of object \"%s\" was cancelled", o));
      } else {
         this.rootCount++;
         this.out.flush();
      }
   }

   public void close() throws IOException {
      this.out.flush();
      this.out.close();
      this.out = null;
   }

   @Override
   public void write(Object o) throws IOException {
      this.write(o, null);
   }

   private void write(Object o, Class clazz) throws IOException {
      try {
         this.depth++;
         if (this.depth > this.maxDepthReached) {
            this.maxDepthReached = this.depth;
            if (this.depth > this.maxDepthAllowed) {
               throw new SerializationException("Serialization is going too deep: " + this.depth);
            }
         }

         this.writeI(o, clazz);
      } finally {
         this.depth--;
      }
   }

   private void writeI(Object key, Class clazz) throws IOException {
      if (!this.cancelled) {
         if (Thread.interrupted()) {
            this.cancelled = true;
         } else {
            boolean b = clazz == null;
            if (key == null) {
               if (!b) {
                  throw new SerializationException("A null object must be a leaf object", clazz);
               }

               this.out.writeIntULEB128(1);
            } else {
               Class clazz2 = b ? key.getClass() : clazz;
               int n = 0;
               if (clazz2.isEnum()) {
                  n |= 2;
                  if (!b) {
                     throw new SerializationException("Enum must be a leaf object", clazz2);
                  }
               }

               if (DynamicEnum.class.isInstance(key)) {
                  n |= 128;
               }

               if (b) {
                  if (clazz2 == Boolean.class) {
                     this.out.writeIntULEB128(64 | ((Boolean)key ? 1 : 0));
                     return;
                  }

                  if (clazz2 == Integer.class) {
                     int intValue = (Integer)key;
                     if (intValue == 0) {
                        this.out.writeIntULEB128(69);
                     } else if (intValue == 1) {
                        this.out.writeIntULEB128(70);
                     } else if (intValue == -1) {
                        this.out.writeIntULEB128(71);
                     } else {
                        this.out.writeIntULEB128(66);
                        this.out.writeIntULEB128((Integer)key);
                     }

                     return;
                  }

                  if (clazz2 == Long.class) {
                     long longValue = (Long)key;
                     if (longValue == 0L) {
                        this.out.writeIntULEB128(72);
                     } else if (longValue == 1L) {
                        this.out.writeIntULEB128(73);
                     } else if (longValue == -1L) {
                        this.out.writeIntULEB128(74);
                     } else {
                        this.out.writeIntULEB128(67);
                        this.out.writeLongULEB128((Long)key);
                     }

                     return;
                  }

                  if (clazz2 == String.class && ((String)key).length() == 0) {
                     this.out.writeIntULEB128(68);
                     return;
                  }

                  if (this.objmap.containsKey(key)) {
                     this.out.writeIntULEB128(n | 4);
                     this.out.writeIntULEB128((Integer)this.objmap.get(key));
                     return;
                  }
               }

               int n2 = 0;

               while (clazz2.isArray()) {
                  Class componentType = clazz2.getComponentType();
                  if (componentType.isPrimitive()) {
                     break;
                  }

                  n2++;
                  clazz2 = componentType;
               }

               String name = null;
               int intValue2 = -1;
               int n3 = this.nativeTypeIdProvider.getId(clazz2);
               if (n3 == 0) {
                  n3 = this.customTypeIdProvider.getId(clazz2);
                  if (n3 == 0 && b) {
                     name = clazz2.getName();
                     Integer n4 = (Integer)this.t2omap.get(name);
                     if (n4 != null) {
                        n |= 512;
                        intValue2 = n4;
                     } else {
                        n |= 256;
                        if (name.startsWith("com.pnfsoftware.jeb") && !name.startsWith("com.pnfsoftware.jeb.test.")) {
                           throw new SerializationException("JEB classes must have a type ID to be serializable", clazz2);
                        }
                     }
                  }
               }

               boolean b2 = n3 >= 0 && n2 == 0;
               boolean b3 = (n & 2) != 0;
               boolean b4 = (n & 128) != 0;
               int n5 = b2 && !b3 && !b4 ? this.getVersionField(key, clazz2) : 0;
               if (n5 != 0) {
                  n |= 32;
               }

               if (!b) {
                  this.out.writeIntULEB128(n);
               } else {
                  n |= 8;
                  if (n2 == 0) {
                     this.out.writeIntULEB128(n);
                  } else {
                     n |= 16;
                     this.out.writeIntULEB128(n);
                     this.out.writeByte(n2);
                  }

                  this.out.writeIntULEB128(this.currentObjectId);
                  this.objmap.put(key, this.currentObjectId);
                  if (n3 == 0 && !this.t2omap.containsKey(name)) {
                     this.t2omap.put(name, this.currentObjectId);
                  }

                  if (this.currentObjectId % 1000000 == 0) {
                     logger.debug("Serializing... status: %d unique objects (%d Kb)", this.currentObjectId, this.out.size() / 1024);
                  }

                  this.currentObjectId++;
                  this.out.writeIntULEB128(n3 + 128);
                  if ((n & 256) != 0) {
                     byte[] encodeUTF8 = Strings.encodeUTF8(name);
                     this.out.writeIntULEB128(encodeUTF8.length);
                     this.out.write(encodeUTF8);
                  } else if ((n & 512) != 0) {
                     this.out.writeIntULEB128(intValue2);
                  }
               }

               if ((n & 2) != 0) {
                  this.out.writeIntULEB128(((Enum)key).ordinal());
                  return;
               }

               if ((n & 128) != 0) {
                  this.out.writeIntULEB128(((DynamicEnum)key).id());
                  return;
               }

               if ((n & 32) != 0) {
                  this.out.writeIntULEB128(n5);
               }

               if (b2) {
                  Class superclass = clazz2.getSuperclass();
                  if (superclass != null) {
                     this.write(key, superclass);
                  }

                  if (!this.attemptCustomWrite(key, clazz2)) {
                     this.writeFields(key, clazz2);
                  }
               } else if (n2 > 0) {
                  int length = Array.getLength(key);
                  this.out.writeIntULEB128(length);

                  for (int i = 0; i < length; i++) {
                     this.write(Array.get(key, i));
                  }
               } else {
                  this.writeNativeObject(key, clazz2);
               }
            }
         }
      }
   }

   @Override
   public void writeFields(Object o, Class clazz) throws IOException {
      List list = (List<Field>)fieldCache.get(clazz);
      if (list == null) {
         list = new ArrayList();
         fieldCache.put(clazz, list);
         HashSet set = new HashSet();

         for (Field field : clazz.getDeclaredFields()) {
            if ((field.getModifiers() & 8) == 0) {
               SerId serId = (SerId)field.getAnnotation(SerId.class);
               if (serId == null) {
                  if (field.getAnnotation(SerTransient.class) == null && !this.impTransientFields.contains(field)) {
                     this.impTransientFields.add(field);
                     logger.debug(Strings.ff("Field %s is implicitly transient", field));
                  }
               } else {
                  int value = serId.value();
                  if (value == 0) {
                     logger.warn(Strings.ff("Invalid field serialization id (0) for field: %s", field));
                  } else if (!serId.deprecated()) {
                     list.add(field);
                     fieldIdCache.put(field, value);
                     if (!set.add(value)) {
                        throw new SerializationException("Duplicate field id: " + value, clazz);
                     }
                  }
               }
            }
         }
      }

      for (Field field2 : list) {
         this.writeField(o, field2, (Integer)fieldIdCache.get(field2));
      }

      this.out.writeIntULEB128(0);
   }

   private void writeField(Object o, Field field, int n) throws IOException {
      boolean canAccess = field.canAccess(o);
      if (!canAccess) {
         field.setAccessible(true);
      }

      Object value;
      try {
         value = field.get(o);
      } catch (IllegalAccessException var7) {
         throw new SerializationException(var7);
      }

      this.out.writeIntULEB128(n);
      this.write(value);
      if (!canAccess) {
         field.setAccessible(false);
      }
   }

   private boolean attemptCustomWrite(Object o, Class clazz) throws SerializationException {
      Class clazz2 = clazz == null ? o.getClass() : clazz;

      try {
         Method method;
         if (customWriters.containsKey(clazz2)) {
            method = (Method)customWriters.get(clazz2);
         } else {
            method = null;

            for (Method method2 : clazz2.getDeclaredMethods()) {
               if (method2.getAnnotation(SerCustomWrite.class) != null) {
                  if (!this.isValidSerializerHelper(method2)) {
                     throw new SerializationException("Invalid prototype for serialization helper method: " + method2, clazz2);
                  }

                  method = method2;
                  break;
               }
            }

            customWriters.put(clazz2, method);
         }

         if (method == null) {
            return false;
         } else {
            ReflectionHelper.invoke2(method, o, new SerializerHelper(this, o, clazz2));
            return true;
         }
      } catch (Exception var9) {
         throw new SerializationException("Custom write failed", var9, clazz2);
      }
   }

   private boolean isValidSerializerHelper(Method method) {
      Class[] parameterTypes = method.getParameterTypes();
      return parameterTypes.length == 1
         && parameterTypes[0] == SerializerHelper.class
         && method.getReturnType() == void.class
         && (method.getModifiers() & 2) != 0;
   }

   private int getVersionField(Object o, Class clazz) throws SerializationException {
      try {
         Class clazz2 = clazz == null ? o.getClass() : clazz;
         Integer n = (Integer)versionFields.get(clazz2);
         if (n == null) {
            SerVersion serVersion = (SerVersion)clazz2.getAnnotation(SerVersion.class);
            if (serVersion != null) {
               n = serVersion.value();
            } else {
               n = 0;
            }

            versionFields.put(clazz2, n);
         }

         return n;
      } catch (SecurityException var6) {
         throw new SerializationException(var6);
      }
   }

   private void writeNativeObject(Object o, Class clazz) throws IOException {
      if (clazz != Object.class) {
         if (clazz == Class.class) {
            String name = null;
            int n = this.nativeTypeIdProvider.getId((Class)o);
            if (n == 0) {
               n = this.customTypeIdProvider.getId((Class)o);
               if (n == 0) {
                  name = ((Class)o).getName();
               }
            }

            this.out.writeInt(n);
            if (name != null) {
               this.out.writeUTF(name);
            }
         } else if (clazz == Boolean.class) {
            this.out.writeByte((Boolean)o ? 1 : 0);
         } else if (clazz == boolean[].class) {
            boolean[] array = (boolean[])o;
            this.out.writeIntULEB128(array.length);
            boolean[] array2 = array;
            int length = array.length;

            for (int i = 0; i < length; i++) {
               this.out.writeByte(array2[i] ? 1 : 0);
            }
         } else if (clazz == Byte.class) {
            this.out.writeByte((Byte)o);
         } else if (clazz == byte[].class) {
            byte[] b = (byte[])o;
            this.out.writeIntULEB128(b.length);
            this.out.write(b);
         } else if (clazz == Short.class) {
            this.out.writeShort((Short)o);
         } else if (clazz == short[].class) {
            short[] array3 = (short[])o;
            this.out.writeIntULEB128(array3.length);
            short[] array4 = array3;
            int length2 = array3.length;

            for (int j = 0; j < length2; j++) {
               this.out.writeShort(array4[j]);
            }
         } else if (clazz == Character.class) {
            this.out.writeChar((Character)o);
         } else if (clazz == char[].class) {
            char[] array5 = (char[])o;
            this.out.writeIntULEB128(array5.length);
            char[] array6 = array5;
            int length3 = array5.length;

            for (int k = 0; k < length3; k++) {
               this.out.writeChar(array6[k]);
            }
         } else if (clazz == Integer.class) {
            this.out.writeIntULEB128((Integer)o);
         } else if (clazz == int[].class) {
            int[] array7 = (int[])o;
            this.out.writeIntULEB128(array7.length);
            int[] array8 = array7;
            int length4 = array7.length;

            for (int l = 0; l < length4; l++) {
               this.out.writeIntULEB128(array8[l]);
            }
         } else if (clazz == Long.class) {
            this.out.writeLongULEB128((Long)o);
         } else if (clazz == long[].class) {
            long[] array9 = (long[])o;
            this.out.writeIntULEB128(array9.length);
            long[] array10 = array9;
            int length5 = array9.length;

            for (int n2 = 0; n2 < length5; n2++) {
               this.out.writeLongULEB128(array10[n2]);
            }
         } else if (clazz == Float.class) {
            this.out.writeFloat((Float)o);
         } else if (clazz == float[].class) {
            float[] array11 = (float[])o;
            this.out.writeIntULEB128(array11.length);
            float[] array12 = array11;
            int length6 = array11.length;

            for (int n3 = 0; n3 < length6; n3++) {
               this.out.writeFloat(array12[n3]);
            }
         } else if (clazz == Double.class) {
            this.out.writeDouble((Double)o);
         } else if (clazz == double[].class) {
            double[] array13 = (double[])o;
            this.out.writeIntULEB128(array13.length);
            double[] array14 = array13;
            int length7 = array13.length;

            for (int n4 = 0; n4 < length7; n4++) {
               this.out.writeDouble(array14[n4]);
            }
         } else if (clazz == String.class) {
            String s = (String)o;
            if (this.stringpool != null) {
               Integer value = (Integer)this.stringpool.get(s);
               if (value == null) {
                  value = this.stringpool.size();
                  this.stringpool.put(s, value);
               }

               this.out.writeIntULEB128(value);
            } else {
               byte[] encodeUTF8 = Strings.encodeUTF8(s);
               this.out.writeIntULEB128(encodeUTF8.length);
               this.out.write(encodeUTF8);
            }
         } else if (clazz == BigInteger.class) {
            byte[] byteArray = ((BigInteger)o).toByteArray();
            this.out.writeIntULEB128(byteArray.length);
            this.out.write(byteArray);
         } else if (clazz == BigDecimal.class) {
            byte[] encodeUTF9 = Strings.encodeUTF8(((BigDecimal)o).toString());
            this.out.writeIntULEB128(encodeUTF9.length);
            this.out.write(encodeUTF9);
         } else if (clazz == AtomicBoolean.class) {
            this.out.writeByte(((AtomicBoolean)o).get() ? 1 : 0);
         } else if (clazz == AtomicInteger.class) {
            this.out.writeIntULEB128(((AtomicInteger)o).get());
         } else if (clazz == AtomicLong.class) {
            this.out.writeLongULEB128(((AtomicLong)o).get());
         } else if (clazz == SoftReference.class) {
            this.write(((SoftReference)o).get());
         } else if (clazz == WeakReference.class) {
            this.write(((WeakReference)o).get());
         } else if (!Collection.class.isAssignableFrom(clazz)
            || clazz != ArrayList.class
               && clazz != LinkedList.class
               && clazz != ArrayDeque.class
               && clazz != HashSet.class
               && clazz != TreeSet.class
               && clazz != LinkedHashSet.class
               && clazz != CopyOnWriteArrayList.class
               && clazz != CopyOnWriteArraySet.class
               && clazz != ConcurrentSkipListSet.class
               && clazz != ConcurrentLinkedQueue.class) {
            if (!Map.class.isAssignableFrom(clazz)
               || clazz != HashMap.class
                  && clazz != TreeMap.class
                  && clazz != LinkedHashMap.class
                  && clazz != IdentityHashMap.class
                  && clazz != ConcurrentHashMap.class
                  && clazz != ConcurrentSkipListMap.class) {
               throw new SerializationException("Native object cannot be serialized", clazz);
            }

            Map map = (Map)o;
            this.out.writeIntULEB128(map.size());

            for (Object next : map.keySet()) {
               Object value2 = map.get(next);
               this.write(next);
               this.write(value2);
            }
         } else {
            Collection collection = (Collection)o;
            this.out.writeIntULEB128(collection.size());
            Iterator iterator = collection.iterator();

            while (iterator.hasNext()) {
               this.write(iterator.next());
            }
         }
      }
   }
}
