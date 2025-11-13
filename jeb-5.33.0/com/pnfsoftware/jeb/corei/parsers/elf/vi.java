package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class vi {
   @SerId(1)
   List pC = new ArrayList();
   @SerId(2)
   List A = new ArrayList();
}
