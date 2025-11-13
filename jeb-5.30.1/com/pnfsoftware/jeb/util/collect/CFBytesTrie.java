package com.pnfsoftware.jeb.util.collect;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.SerializerHelper;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomWrite;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

@Ser
public class CFBytesTrie {
   @SerId(1)
   private CFBytesTrie.IKeyExtractor keyExtractor;
   @SerId(2)
   private CFBytesTrie.Node[] array0;
   @SerId(3)
   private int cnt;
   @SerId(4)
   private List valuesForStorage;

   @SerCustomWrite
   private void save(SerializerHelper var1) throws IOException {
      if (this.keyExtractor == null) {
         var1.saveStandard();
      } else {
         this.valuesForStorage = this.getValues();
         if (this.valuesForStorage.size() != this.cnt) {
            throw new RuntimeException(Strings.ff("Unexpected size: actual=%d, expected=%d", this.valuesForStorage.size(), this.cnt));
         } else {
            CFBytesTrie.Node[] var2 = this.array0;
            this.array0 = null;
            int var3 = this.cnt;
            this.cnt = 0;
            var1.saveStandard();
            this.valuesForStorage = null;
            this.array0 = var2;
            this.cnt = var3;
         }
      }
   }

   @SerCustomInitPostGraph
   private void postInit() {
      if (this.keyExtractor != null) {
         for (Object var2 : this.valuesForStorage) {
            this.put(var2);
         }

         this.valuesForStorage = null;
      }
   }

   public void setKeyExtractor(CFBytesTrie.IKeyExtractor var1) {
      this.keyExtractor = var1;
   }

   public CFBytesTrie.IKeyExtractor getKeyExtractor() {
      return this.keyExtractor;
   }

   public void clear() {
      this.array0 = null;
      this.cnt = 0;
   }

   public int size() {
      return this.cnt;
   }

   public boolean isEmpty() {
      return this.cnt == 0;
   }

   public void put(Object var1) {
      if (this.keyExtractor == null) {
         throw new IllegalStateException();
      } else {
         this.put(this.keyExtractor.extract(var1), var1);
      }
   }

   public void put(byte[] var1, Object var2) {
      this.put(var1, 0, var1.length, var2);
   }

   public Object put(byte[] var1, int var2, int var3, Object var4) {
      if (var4 == null) {
         throw new NullPointerException("The value cannot be null");
      } else if (var2 >= var3) {
         throw new IllegalArgumentException("Needs at least one byte of key data");
      } else {
         int var5 = var1[var2] & 255;
         if (this.array0 == null) {
            this.array0 = new CFBytesTrie.Node[256];
         }

         CFBytesTrie.Node var6 = this.array0[var5];
         if (var6 == null) {
            var6 = new CFBytesTrie.Node();
            this.array0[var5] = var6;
         }

         for (int var7 = var2 + 1; var7 < var3; var7++) {
            int var8 = var1[var7] & 15;
            if (var6.next == null) {
               if (var6.object != null) {
                  throw new IllegalStateException("Prefix collision");
               }

               var6.next = new CFBytesTrie.Node[16];
            }

            CFBytesTrie.Node var9 = var6.next[var8];
            if (var9 == null) {
               var9 = new CFBytesTrie.Node();
               var6.next[var8] = var9;
            }

            int var10 = var1[var7] >> 4 & 15;
            if (var9.next == null) {
               if (var9.object != null) {
                  throw new IllegalStateException("Prefix collision");
               }

               var9.next = new CFBytesTrie.Node[16];
            }

            var9 = var9.next[var10];
            if (var9 == null) {
               var9 = new CFBytesTrie.Node();
               var9.next[var10] = var9;
            }

            var6 = var9;
         }

         if (var6.next != null) {
            throw new IllegalStateException("Prefix collision");
         } else {
            Object var12 = var6.object;
            if (var12 == null) {
               this.cnt++;
            }

            var6.object = var4;
            return var12;
         }
      }
   }

   public boolean putSafe(byte[] var1, int var2, int var3, Object var4, Object[] var5) {
      try {
         Object var6 = this.put(var1, var2, var3, var4);
         if (var5 != null) {
            var5[0] = var6;
         }

         return true;
      } catch (IllegalStateException var7) {
         return false;
      }
   }

   public Object get(byte[] var1, boolean var2) {
      return this.get(var1, 0, var1.length, var2);
   }

   public Object get(byte[] var1, int var2, int var3, boolean var4) {
      Object var5 = null;
      if (var2 >= var3) {
         throw new IllegalArgumentException("Needs at least one byte of key data");
      } else if (this.array0 == null) {
         return null;
      } else {
         int var6 = var1[var2] & 255;
         CFBytesTrie.Node var7 = this.array0[var6];
         if (var7 == null) {
            return null;
         } else {
            if (var7.object != null) {
               var5 = var7.object;
            }

            int var8 = var2 + 1;
            if (var5 == null) {
               while (var8 < var3) {
                  if (var7.next == null) {
                     return null;
                  }

                  int var9 = var1[var8] & 15;
                  var7 = var7.next[var9];
                  if (var7 == null) {
                     return null;
                  }

                  if (var7.object != null) {
                     var5 = var7.object;
                     break;
                  }

                  if (var7.next == null) {
                     return null;
                  }

                  int var10 = var1[var8] >> 4 & 15;
                  var7 = var7.next[var10];
                  if (var7 == null) {
                     return null;
                  }

                  if (var7.object != null) {
                     var5 = var7.object;
                     break;
                  }

                  var8++;
               }
            }

            return var4 && var8 != var3 ? null : var5;
         }
      }
   }

   public String formatInternalState() {
      CFBytesTrie.State var1 = new CFBytesTrie.State();
      if (this.array0 != null) {
         var1.nodeCount = 1;

         for (CFBytesTrie.Node var5 : this.array0) {
            this.checkNodeRecurse(var5, var1, 1);
         }
      }

      StringBuilder var6 = new StringBuilder();
      double var7 = -1.0;
      if (var1.nodeCount > 0) {
         var7 = (double)var1.objectCount / (256 + (var1.nodeCount - 1) * 16);
      }

      Strings.ff(var6, "cnt=%d nodes=%d depth=%d occupancy=%.3f", var1.objectCount, var1.nodeCount, var1.maxDepth, var7);
      return var6.toString();
   }

   private void checkNodeRecurse(CFBytesTrie.Node var1, CFBytesTrie.State var2, int var3) {
      if (var1 != null) {
         if (var3 > var2.maxDepth) {
            var2.maxDepth = var3;
         }

         if (var1.object != null) {
            var2.objectCount++;
         }

         if (var1.next != null) {
            var2.nodeCount++;

            for (CFBytesTrie.Node var7 : var1.next) {
               this.checkNodeRecurse(var7, var2, var3 + 1);
            }
         }
      }
   }

   public List getValues() {
      ArrayList var1 = new ArrayList();
      if (this.array0 != null) {
         for (CFBytesTrie.Node var5 : this.array0) {
            this.getValuesRecurse(var5, var1);
         }
      }

      return var1;
   }

   private void getValuesRecurse(CFBytesTrie.Node var1, List var2) {
      if (var1 != null) {
         if (var1.object != null) {
            var2.add(var1.object);
         }

         if (var1.next != null) {
            for (CFBytesTrie.Node var6 : var1.next) {
               this.getValuesRecurse(var6, var2);
            }
         }
      }
   }

   public List getItems() {
      ArrayList var1 = new ArrayList();
      if (this.array0 != null) {
         ArrayDeque var2 = new ArrayDeque();
         int var3 = 0;

         for (CFBytesTrie.Node var7 : this.array0) {
            var2.push((byte)var3);
            this.getItemsRecurse(var7, var1, var2);
            var2.pop();
            var3++;
         }
      }

      return var1;
   }

   private void getItemsRecurse(CFBytesTrie.Node var1, List var2, Deque var3) {
      if (var1 != null) {
         if (var1.object != null) {
            var2.add(new Couple(this.buildKey(var3), var1.object));
         }

         if (var1.next != null) {
            int var4 = 0;

            for (CFBytesTrie.Node var8 : var1.next) {
               var3.push((byte)var4);
               this.getItemsRecurse(var8, var2, var3);
               var3.pop();
               var4++;
            }
         }
      }
   }

   private byte[] buildKey(Deque var1) {
      int var2 = var1.size();
      Assert.a(var2 >= 1 && var2 % 2 == 1);
      byte[] var3 = new byte[1 + (var2 - 1) / 2];
      int var4 = 0;
      Iterator var5 = var1.descendingIterator();

      for (int var6 = 1; var5.hasNext(); var4++) {
         byte var7 = (Byte)var5.next();
         if (var4 == 0) {
            var3[0] = var7;
         } else if (var4 % 2 != 0) {
            var3[var6] = var7;
         } else {
            var3[var6] = (byte)(var3[var6] | var7 << 4);
            var6++;
         }
      }

      return var3;
   }

   @Ser
   public interface IKeyExtractor {
      byte[] extract(Object var1);
   }

   @Ser
   public static class Node {
      @SerId(1)
      CFBytesTrie.Node[] next;
      @SerId(2)
      Object object;
   }

   static class State {
      int objectCount;
      int nodeCount;
      int maxDepth;
   }
}
