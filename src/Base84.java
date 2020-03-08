import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Base84
{
  private static final char[] b64e = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 
    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
    'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
  
  public static String encodeBase64(byte[] b, int off, int len)
  {
    int i = 0;
    StringBuffer s = new StringBuffer();
    while (len >= 3)
    {
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[(off + 2)] & 0xFF;
      s.append(b64e[(i >> 18 & 0x3F)]);
      s.append(b64e[(i >> 12 & 0x3F)]);
      s.append(b64e[(i >> 6 & 0x3F)]);
      s.append(b64e[(i & 0x3F)]);
      off += 3;
      len -= 3;
    }
    switch (len)
    {
    case 2: 
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8;
      s.append(b64e[(i >> 18 & 0x3F)]);
      s.append(b64e[(i >> 12 & 0x3F)]);
      s.append(b64e[(i >> 6 & 0x3F)]);
      s.append('=');
      break;
    case 1: 
      i = (b[off] & 0xFF) << 16;
      s.append(b64e[(i >> 18 & 0x3F)]);
      s.append(b64e[(i >> 12 & 0x3F)]);
      s.append('=');
      s.append('=');
    }
    return s.toString();
  }
  
  public static String encodeBase64(byte[] b)
  {
    return encodeBase64(b, 0, b.length);
  }
  
  private static int b64d(char i)
  {
    switch (i)
    {
    case 'A': 
      return 0;
    case 'B': 
      return 1;
    case 'C': 
      return 2;
    case 'D': 
      return 3;
    case 'E': 
      return 4;
    case 'F': 
      return 5;
    case 'G': 
      return 6;
    case 'H': 
      return 7;
    case 'I': 
      return 8;
    case 'J': 
      return 9;
    case 'K': 
      return 10;
    case 'L': 
      return 11;
    case 'M': 
      return 12;
    case 'N': 
      return 13;
    case 'O': 
      return 14;
    case 'P': 
      return 15;
    case 'Q': 
      return 16;
    case 'R': 
      return 17;
    case 'S': 
      return 18;
    case 'T': 
      return 19;
    case 'U': 
      return 20;
    case 'V': 
      return 21;
    case 'W': 
      return 22;
    case 'X': 
      return 23;
    case 'Y': 
      return 24;
    case 'Z': 
      return 25;
    case 'a': 
      return 26;
    case 'b': 
      return 27;
    case 'c': 
      return 28;
    case 'd': 
      return 29;
    case 'e': 
      return 30;
    case 'f': 
      return 31;
    case 'g': 
      return 32;
    case 'h': 
      return 33;
    case 'i': 
      return 34;
    case 'j': 
      return 35;
    case 'k': 
      return 36;
    case 'l': 
      return 37;
    case 'm': 
      return 38;
    case 'n': 
      return 39;
    case 'o': 
      return 40;
    case 'p': 
      return 41;
    case 'q': 
      return 42;
    case 'r': 
      return 43;
    case 's': 
      return 44;
    case 't': 
      return 45;
    case 'u': 
      return 46;
    case 'v': 
      return 47;
    case 'w': 
      return 48;
    case 'x': 
      return 49;
    case 'y': 
      return 50;
    case 'z': 
      return 51;
    case '0': 
      return 52;
    case '1': 
      return 53;
    case '2': 
      return 54;
    case '3': 
      return 55;
    case '4': 
      return 56;
    case '5': 
      return 57;
    case '6': 
      return 58;
    case '7': 
      return 59;
    case '8': 
      return 60;
    case '9': 
      return 61;
    case '+': 
      return 62;
    case '/': 
      return 63;
    }
    return -1;
  }
  
  public static byte[] decodeBase64(String s)
  {
    CharacterIterator it = new StringCharacterIterator(s.trim());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int i = 0;int j = 0;
    for (char ch = it.first(); (ch != 65535) && (ch != '='); ch = it.next())
    {
      int v = b64d(ch);
      if (v >= 0)
      {
        i = i << 6 | v;
        j++;
        if (j >= 4)
        {
          out.write(i >> 16);
          out.write(i >> 8);
          out.write(i);
          i = 0;j = 0;
        }
      }
    }
    switch (j)
    {
    case 3: 
      out.write(i >> 10);
      out.write(i >> 2);
      break;
    case 2: 
      out.write(i >> 4);
    }
    return out.toByteArray();
  }
  
  public static String encodeUU(byte[] b, int off, int len)
  {
    int i = 0;
    StringBuffer s = new StringBuffer();
    while (len > 45)
    {
      s.append('M');
      for (int j = 0; j < 45; j += 3)
      {
        i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[(off + 2)] & 0xFF;
        s.append((char)(32 + (i >> 18 & 0x3F)));
        s.append((char)(32 + (i >> 12 & 0x3F)));
        s.append((char)(32 + (i >> 6 & 0x3F)));
        s.append((char)(32 + (i & 0x3F)));
        off += 3;
        len -= 3;
      }
      s.append('\n');
    }
    s.append((char)(32 + len));
    while (len >= 3)
    {
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[(off + 2)] & 0xFF;
      s.append((char)(32 + (i >> 18 & 0x3F)));
      s.append((char)(32 + (i >> 12 & 0x3F)));
      s.append((char)(32 + (i >> 6 & 0x3F)));
      s.append((char)(32 + (i & 0x3F)));
      off += 3;
      len -= 3;
    }
    switch (len)
    {
    case 2: 
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8;
      s.append((char)(32 + (i >> 18 & 0x3F)));
      s.append((char)(32 + (i >> 12 & 0x3F)));
      s.append((char)(32 + (i >> 6 & 0x3F)));
      break;
    case 1: 
      i = (b[off] & 0xFF) << 16;
      s.append((char)(32 + (i >> 18 & 0x3F)));
      s.append((char)(32 + (i >> 12 & 0x3F)));
    }
    return s.toString();
  }
  
  public static String encodeUU(byte[] b)
  {
    return encodeUU(b, 0, b.length);
  }
  
  public static byte[] decodeUU(String s)
  {
    s = s.replaceAll("\r\n|\r|\n|?|?", "\n").trim();
    if ((s.startsWith("begin ")) && (s.endsWith("\nend")))
    {
      int o = s.indexOf('\n');
      int e = s.length() - 4;
      s = s.substring(o, e).trim();
    }
    CharacterIterator it = new StringCharacterIterator(s);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int i = 0;int j = 0;
    for (char ch = it.first(); (ch != 65535) && (ch != '`'); ch = it.next()) {
      if ((ch > ' ') && (ch < '`')) {
        for (;;)
        {
          int v = it.next() - ' ';
          if ((v < 0) || (v >= 64)) {
            break;
          }
          i = i << 6 | v;
          j++;
          if (j >= 4)
          {
            out.write(i >> 16);
            out.write(i >> 8);
            out.write(i);
            i = 0;j = 0;
          }
        }
      }
    }
    switch (j)
    {
    case 3: 
      out.write(i >> 10);
      out.write(i >> 2);
      break;
    case 2: 
      out.write(i >> 4);
    }
    return out.toByteArray();
  }
  
  private static final char[] xxe = {
    '+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 
    'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
    'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
  
  public static String encodeXX(byte[] b, int off, int len)
  {
    int i = 0;
    StringBuffer s = new StringBuffer();
    while (len > 45)
    {
      s.append(xxe[45]);
      for (int j = 0; j < 45; j += 3)
      {
        i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[(off + 2)] & 0xFF;
        s.append(xxe[(i >> 18 & 0x3F)]);
        s.append(xxe[(i >> 12 & 0x3F)]);
        s.append(xxe[(i >> 6 & 0x3F)]);
        s.append(xxe[(i & 0x3F)]);
        off += 3;
        len -= 3;
      }
      s.append('\n');
    }
    s.append(xxe[len]);
    while (len >= 3)
    {
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[(off + 2)] & 0xFF;
      s.append(xxe[(i >> 18 & 0x3F)]);
      s.append(xxe[(i >> 12 & 0x3F)]);
      s.append(xxe[(i >> 6 & 0x3F)]);
      s.append(xxe[(i & 0x3F)]);
      off += 3;
      len -= 3;
    }
    switch (len)
    {
    case 2: 
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8;
      s.append(xxe[(i >> 18 & 0x3F)]);
      s.append(xxe[(i >> 12 & 0x3F)]);
      s.append(xxe[(i >> 6 & 0x3F)]);
      break;
    case 1: 
      i = (b[off] & 0xFF) << 16;
      s.append(xxe[(i >> 18 & 0x3F)]);
      s.append(xxe[(i >> 12 & 0x3F)]);
    }
    return s.toString();
  }
  
  public static String encodeXX(byte[] b)
  {
    return encodeXX(b, 0, b.length);
  }
  
  private static int xxd(char i)
  {
    switch (i)
    {
    case '+': 
      return 0;
    case '-': 
      return 1;
    case '0': 
      return 2;
    case '1': 
      return 3;
    case '2': 
      return 4;
    case '3': 
      return 5;
    case '4': 
      return 6;
    case '5': 
      return 7;
    case '6': 
      return 8;
    case '7': 
      return 9;
    case '8': 
      return 10;
    case '9': 
      return 11;
    case 'A': 
      return 12;
    case 'B': 
      return 13;
    case 'C': 
      return 14;
    case 'D': 
      return 15;
    case 'E': 
      return 16;
    case 'F': 
      return 17;
    case 'G': 
      return 18;
    case 'H': 
      return 19;
    case 'I': 
      return 20;
    case 'J': 
      return 21;
    case 'K': 
      return 22;
    case 'L': 
      return 23;
    case 'M': 
      return 24;
    case 'N': 
      return 25;
    case 'O': 
      return 26;
    case 'P': 
      return 27;
    case 'Q': 
      return 28;
    case 'R': 
      return 29;
    case 'S': 
      return 30;
    case 'T': 
      return 31;
    case 'U': 
      return 32;
    case 'V': 
      return 33;
    case 'W': 
      return 34;
    case 'X': 
      return 35;
    case 'Y': 
      return 36;
    case 'Z': 
      return 37;
    case 'a': 
      return 38;
    case 'b': 
      return 39;
    case 'c': 
      return 40;
    case 'd': 
      return 41;
    case 'e': 
      return 42;
    case 'f': 
      return 43;
    case 'g': 
      return 44;
    case 'h': 
      return 45;
    case 'i': 
      return 46;
    case 'j': 
      return 47;
    case 'k': 
      return 48;
    case 'l': 
      return 49;
    case 'm': 
      return 50;
    case 'n': 
      return 51;
    case 'o': 
      return 52;
    case 'p': 
      return 53;
    case 'q': 
      return 54;
    case 'r': 
      return 55;
    case 's': 
      return 56;
    case 't': 
      return 57;
    case 'u': 
      return 58;
    case 'v': 
      return 59;
    case 'w': 
      return 60;
    case 'x': 
      return 61;
    case 'y': 
      return 62;
    case 'z': 
      return 63;
    }
    return -1;
  }
  
  public static byte[] decodeXX(String s)
  {
    s = s.replaceAll("\r\n|\r|\n|?|?", "\n").trim();
    if ((s.startsWith("begin ")) && (s.endsWith("\nend")))
    {
      int o = s.indexOf('\n');
      int e = s.length() - 4;
      s = s.substring(o, e).trim();
    }
    CharacterIterator it = new StringCharacterIterator(s);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int i = 0;int j = 0;
    for (char ch = it.first(); (ch != 65535) && (ch != '+'); ch = it.next()) {
      if (xxd(ch) >= 0) {
        for (;;)
        {
          int v = xxd(it.next());
          if (v < 0) {
            break;
          }
          i = i << 6 | v;
          j++;
          if (j >= 4)
          {
            out.write(i >> 16);
            out.write(i >> 8);
            out.write(i);
            i = 0;j = 0;
          }
        }
      }
    }
    switch (j)
    {
    case 3: 
      out.write(i >> 10);
      out.write(i >> 2);
      break;
    case 2: 
      out.write(i >> 4);
    }
    return out.toByteArray();
  }
  
  private static final char[] hqxe = {
    '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '0', '1', '2', 
    '3', '4', '5', '6', '8', '9', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
    'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z', '[', 
    '`', 'a', 'b', 'c', 'd', 'e', 'f', 'h', 'i', 'j', 'k', 'l', 'm', 'p', 'q', 'r' };
  
  public static String encodeBinHex(byte[] b, int off, int len)
  {
    ByteArrayOutputStream iout = new ByteArrayOutputStream();
    while (len > 0)
    {
      byte v = b[(off++)];
      int r = 1;
      len--;
      while ((len > 0) && (b[off] == v) && (r < 255))
      {
        off++;
        r++;
        len--;
      }
      if (r > 2)
      {
        if (v == -112)
        {
          iout.write(144);
          iout.write(0);
        }
        else
        {
          iout.write(v);
        }
        iout.write(144);
        iout.write(r);
      }
    }
    b = iout.toByteArray();
    off = 0;
    len = b.length;
    
    int i = 0;
    StringBuffer s = new StringBuffer();
    s.append(':');
    while (len >= 3)
    {
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[(off + 2)] & 0xFF;
      s.append(hqxe[(i >> 18 & 0x3F)]);
      s.append(hqxe[(i >> 12 & 0x3F)]);
      s.append(hqxe[(i >> 6 & 0x3F)]);
      s.append(hqxe[(i & 0x3F)]);
      off += 3;
      len -= 3;
    }
    switch (len)
    {
    case 2: 
      i = (b[off] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8;
      s.append(hqxe[(i >> 18 & 0x3F)]);
      s.append(hqxe[(i >> 12 & 0x3F)]);
      s.append(hqxe[(i >> 6 & 0x3F)]);
      break;
    case 1: 
      i = (b[off] & 0xFF) << 16;
      s.append(hqxe[(i >> 18 & 0x3F)]);
      s.append(hqxe[(i >> 12 & 0x3F)]);
    }
    s.append(':');
    return s.toString();
  }
  
  public static String encodeBinHex(byte[] b)
  {
    return encodeBinHex(b, 0, b.length);
  }
  
  private static int hqxd(char i)
  {
    switch (i)
    {
    case '!': 
      return 0;
    case '"': 
      return 1;
    case '#': 
      return 2;
    case '$': 
      return 3;
    case '%': 
      return 4;
    case '&': 
      return 5;
    case '\'': 
      return 6;
    case '(': 
      return 7;
    case ')': 
      return 8;
    case '*': 
      return 9;
    case '+': 
      return 10;
    case ',': 
      return 11;
    case '-': 
      return 12;
    case '0': 
      return 13;
    case '1': 
      return 14;
    case '2': 
      return 15;
    case '3': 
      return 16;
    case '4': 
      return 17;
    case '5': 
      return 18;
    case '6': 
      return 19;
    case '8': 
      return 20;
    case '9': 
      return 21;
    case '@': 
      return 22;
    case 'A': 
      return 23;
    case 'B': 
      return 24;
    case 'C': 
      return 25;
    case 'D': 
      return 26;
    case 'E': 
      return 27;
    case 'F': 
      return 28;
    case 'G': 
      return 29;
    case 'H': 
      return 30;
    case 'I': 
      return 31;
    case 'J': 
      return 32;
    case 'K': 
      return 33;
    case 'L': 
      return 34;
    case 'M': 
      return 35;
    case 'N': 
      return 36;
    case 'P': 
      return 37;
    case 'Q': 
      return 38;
    case 'R': 
      return 39;
    case 'S': 
      return 40;
    case 'T': 
      return 41;
    case 'U': 
      return 42;
    case 'V': 
      return 43;
    case 'X': 
      return 44;
    case 'Y': 
      return 45;
    case 'Z': 
      return 46;
    case '[': 
      return 47;
    case '`': 
      return 48;
    case 'a': 
      return 49;
    case 'b': 
      return 50;
    case 'c': 
      return 51;
    case 'd': 
      return 52;
    case 'e': 
      return 53;
    case 'f': 
      return 54;
    case 'h': 
      return 55;
    case 'i': 
      return 56;
    case 'j': 
      return 57;
    case 'k': 
      return 58;
    case 'l': 
      return 59;
    case 'm': 
      return 60;
    case 'p': 
      return 61;
    case 'q': 
      return 62;
    case 'r': 
      return 63;
    }
    return -1;
  }
  
  public static byte[] decodeBinHex(String s)
  {
    CharacterIterator it = new StringCharacterIterator(s.trim());
    ByteArrayOutputStream iout = new ByteArrayOutputStream();
    int i = 0;int j = 0;
    char ch = it.first();
    if (ch == ':') {
      ch = it.next();
    }
    while ((ch != 65535) && (ch != ':'))
    {
      int v = hqxd(ch);
      if (v >= 0)
      {
        i = i << 6 | v;
        j++;
        if (j >= 4)
        {
          iout.write(i >> 16);
          iout.write(i >> 8);
          iout.write(i);
          i = 0;j = 0;
        }
      }
      ch = it.next();
    }
    switch (j)
    {
    case 3: 
      iout.write(i >> 10);
      iout.write(i >> 2);
      break;
    case 2: 
      iout.write(i >> 4);
    }
    byte[] b = iout.toByteArray();
    
    int off = 0;
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    byte last = 0;
    while (off < b.length)
    {
      byte v = b[(off++)];
      if ((v == -112) && (off < b.length))
      {
        int r = b[(off++)] & 0xFF;
        if (r == 0)
        {
          out.write(last = -112);
        }
        else
        {
          r--;
          while (r-- > 0) {
            out.write(last);
          }
        }
      }
      else
      {
        out.write(last = v);
      }
    }
    return out.toByteArray();
  }
  
  public static String encodeASCII85(byte[] b, int off, int len)
  {
    long i = 0L;
    StringBuffer s = new StringBuffer();
    s.append("<~");
    while (len >= 4)
    {
      i = (b[off] & 0xFF) << 24 | (b[(off + 1)] & 0xFF) << 16 | (b[(off + 2)] & 0xFF) << 8 | b[(off + 3)] & 0xFF;
      if (i == 0L)
      {
        s.append('z');
      }
      else
      {
        s.append((char)(int)(33L + i / 52200625L % 85L));
        s.append((char)(int)(33L + i / 614125L % 85L));
        s.append((char)(int)(33L + i / 7225L % 85L));
        s.append((char)(int)(33L + i / 85L % 85L));
        s.append((char)(int)(33L + i % 85L));
      }
      off += 4;
      len -= 4;
    }
    switch (len)
    {
    case 3: 
      i = (b[off] & 0xFF) << 24 | (b[(off + 1)] & 0xFF) << 16 | (b[(off + 2)] & 0xFF) << 8;
      s.append((char)(int)(33L + i / 52200625L % 85L));
      s.append((char)(int)(33L + i / 614125L % 85L));
      s.append((char)(int)(33L + i / 7225L % 85L));
      s.append((char)(int)(33L + i / 85L % 85L));
      break;
    case 2: 
      i = (b[off] & 0xFF) << 24 | (b[(off + 1)] & 0xFF) << 16;
      s.append((char)(int)(33L + i / 52200625L % 85L));
      s.append((char)(int)(33L + i / 614125L % 85L));
      s.append((char)(int)(33L + i / 7225L % 85L));
      break;
    case 1: 
      i = (b[off] & 0xFF) << 24;
      s.append((char)(int)(33L + i / 52200625L % 85L));
      s.append((char)(int)(33L + i / 614125L % 85L));
    }
    s.append("~>");
    return s.toString();
  }
  
  public static String encodeASCII85(byte[] b)
  {
    return encodeASCII85(b, 0, b.length);
  }
  
  public static byte[] decodeASCII85(String s)
  {
    s = s.trim();
    if ((s.startsWith("<~")) && (s.endsWith("~>"))) {
      s = s.substring(2, s.length() - 2).trim();
    }
    CharacterIterator it = new StringCharacterIterator(s);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    long i = 0L;int j = 0;
    for (char ch = it.first(); (ch != 65535) && (ch != '~'); ch = it.next()) {
      if ((ch == 'z') && (j == 0))
      {
        out.write(0);
        out.write(0);
        out.write(0);
        out.write(0);
      }
      else if ((ch == 'y') && (j == 0))
      {
        out.write(32);
        out.write(32);
        out.write(32);
        out.write(32);
      }
      else if ((ch == 'x') && (j == 0))
      {
        out.write(-1);
        out.write(-1);
        out.write(-1);
        out.write(-1);
      }
      else if ((ch >= '!') && (ch <= 'u'))
      {
        i = i * 85L + (ch - '!');
        j++;
        if (j >= 5)
        {
          out.write((int)(i >> 24));
          out.write((int)(i >> 16));
          out.write((int)(i >> 8));
          out.write((int)i);
          i = 0L;j = 0;
        }
      }
    }
    switch (j)
    {
    case 4: 
      i = i * 85L + 84L;
      out.write((int)(i >> 24));
      out.write((int)(i >> 16));
      out.write((int)(i >> 8));
      break;
    case 3: 
      i = i * 85L + 84L;
      i = i * 85L + 84L;
      out.write((int)(i >> 24));
      out.write((int)(i >> 16));
      break;
    case 2: 
      i = i * 85L + 84L;
      i = i * 85L + 84L;
      i = i * 85L + 84L;
      out.write((int)(i >> 24));
    }
    return out.toByteArray();
  }
  
  private static final char[] k85e = {
    '!', '#', '$', '%', '&', '(', ')', '+', ',', '-', '.', '0', '1', '2', '3', '4', '5', 
    '6', '7', '8', '9', ':', ';', '=', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 
    'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
    '[', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
    'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '{', '}', '~' };
  
  public static String encodeKreative85(byte[] b, int off, int len)
  {
    long i = 0L;
    StringBuffer s = new StringBuffer();
    while (len >= 4)
    {
      i = (b[off] & 0xFF) << 24 | (b[(off + 1)] & 0xFF) << 16 | (b[(off + 2)] & 0xFF) << 8 | b[(off + 3)] & 0xFF;
      s.append(k85e[((int)(i / 52200625L % 85L))]);
      s.append(k85e[((int)(i / 614125L % 85L))]);
      s.append(k85e[((int)(i / 7225L % 85L))]);
      s.append(k85e[((int)(i / 85L % 85L))]);
      s.append(k85e[((int)(i % 85L))]);
      off += 4;
      len -= 4;
    }
    switch (len)
    {
    case 3: 
      i = (b[off] & 0xFF) << 24 | (b[(off + 1)] & 0xFF) << 16 | (b[(off + 2)] & 0xFF) << 8;
      s.append(k85e[((int)(i / 52200625L % 85L))]);
      s.append(k85e[((int)(i / 614125L % 85L))]);
      s.append(k85e[((int)(i / 7225L % 85L))]);
      s.append(k85e[((int)(i / 85L % 85L))]);
      break;
    case 2: 
      i = (b[off] & 0xFF) << 24 | (b[(off + 1)] & 0xFF) << 16;
      s.append(k85e[((int)(i / 52200625L % 85L))]);
      s.append(k85e[((int)(i / 614125L % 85L))]);
      s.append(k85e[((int)(i / 7225L % 85L))]);
      break;
    case 1: 
      i = (b[off] & 0xFF) << 24;
      s.append(k85e[((int)(i / 52200625L % 85L))]);
      s.append(k85e[((int)(i / 614125L % 85L))]);
    }
    return s.toString();
  }
  
  public static String encodeKreative85(byte[] b)
  {
    return encodeKreative85(b, 0, b.length);
  }
  
  public static String encodeLegacy85(byte[] b, int off, int len)
  {
    long i = 0L;
    StringBuffer s = new StringBuffer();
    s.append('<');
    s.append(k85e[((int)(len % 85L))]);
    s.append(k85e[((int)(len / 85L % 85L))]);
    s.append(k85e[((int)(len / 7225L % 85L))]);
    s.append(k85e[((int)(len / 614125L % 85L))]);
    s.append(k85e[((int)(len / 52200625L % 85L))]);
    s.append('>');
    while (len >= 4)
    {
      i = (b[(off + 3)] & 0xFF) << 24 | (b[(off + 2)] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[off] & 0xFF;
      s.append(k85e[((int)(i % 85L))]);
      s.append(k85e[((int)(i / 85L % 85L))]);
      s.append(k85e[((int)(i / 7225L % 85L))]);
      s.append(k85e[((int)(i / 614125L % 85L))]);
      s.append(k85e[((int)(i / 52200625L % 85L))]);
      off += 4;
      len -= 4;
    }
    if (len > 0)
    {
      switch (len)
      {
      case 3: 
        i = (b[(off + 2)] & 0xFF) << 16 | (b[(off + 1)] & 0xFF) << 8 | b[off] & 0xFF;
        break;
      case 2: 
        i = (b[(off + 1)] & 0xFF) << 8 | b[off] & 0xFF;
        break;
      case 1: 
        i = b[off] & 0xFF;
      }
      s.append(k85e[((int)(i % 85L))]);
      s.append(k85e[((int)(i / 85L % 85L))]);
      s.append(k85e[((int)(i / 7225L % 85L))]);
      s.append(k85e[((int)(i / 614125L % 85L))]);
      s.append(k85e[((int)(i / 52200625L % 85L))]);
    }
    return s.toString();
  }
  
  public static String encodeLegacy85(byte[] b)
  {
    return encodeLegacy85(b, 0, b.length);
  }
  
  private static int k85d(char i)
  {
    switch (i)
    {
    case '!': 
      return 0;
    case '#': 
      return 1;
    case '$': 
      return 2;
    case '%': 
      return 3;
    case '&': 
      return 4;
    case '(': 
      return 5;
    case ')': 
      return 6;
    case '+': 
      return 7;
    case ',': 
      return 8;
    case '-': 
      return 9;
    case '.': 
      return 10;
    case '0': 
      return 11;
    case '1': 
      return 12;
    case '2': 
      return 13;
    case '3': 
      return 14;
    case '4': 
      return 15;
    case '5': 
      return 16;
    case '6': 
      return 17;
    case '7': 
      return 18;
    case '8': 
      return 19;
    case '9': 
      return 20;
    case ':': 
      return 21;
    case ';': 
      return 22;
    case '=': 
      return 23;
    case '@': 
      return 24;
    case 'A': 
      return 25;
    case 'B': 
      return 26;
    case 'C': 
      return 27;
    case 'D': 
      return 28;
    case 'E': 
      return 29;
    case 'F': 
      return 30;
    case 'G': 
      return 31;
    case 'H': 
      return 32;
    case 'I': 
      return 33;
    case 'J': 
      return 34;
    case 'K': 
      return 35;
    case 'L': 
      return 36;
    case 'M': 
      return 37;
    case 'N': 
      return 38;
    case 'O': 
      return 39;
    case 'P': 
      return 40;
    case 'Q': 
      return 41;
    case 'R': 
      return 42;
    case 'S': 
      return 43;
    case 'T': 
      return 44;
    case 'U': 
      return 45;
    case 'V': 
      return 46;
    case 'W': 
      return 47;
    case 'X': 
      return 48;
    case 'Y': 
      return 49;
    case 'Z': 
      return 50;
    case '[': 
      return 51;
    case ']': 
      return 52;
    case '^': 
      return 53;
    case '_': 
      return 54;
    case '`': 
      return 55;
    case 'a': 
      return 56;
    case 'b': 
      return 57;
    case 'c': 
      return 58;
    case 'd': 
      return 59;
    case 'e': 
      return 60;
    case 'f': 
      return 61;
    case 'g': 
      return 62;
    case 'h': 
      return 63;
    case 'i': 
      return 64;
    case 'j': 
      return 65;
    case 'k': 
      return 66;
    case 'l': 
      return 67;
    case 'm': 
      return 68;
    case 'n': 
      return 69;
    case 'o': 
      return 70;
    case 'p': 
      return 71;
    case 'q': 
      return 72;
    case 'r': 
      return 73;
    case 's': 
      return 74;
    case 't': 
      return 75;
    case 'u': 
      return 76;
    case 'v': 
      return 77;
    case 'w': 
      return 78;
    case 'x': 
      return 79;
    case 'y': 
      return 80;
    case 'z': 
      return 81;
    case '{': 
      return 82;
    case '}': 
      return 83;
    case '~': 
      return 84;
    }
    return -1;
  }
  
  public static byte[] decodeKreative85(String s)
  {
    CharacterIterator it = new StringCharacterIterator(s.trim());
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    long i = 0L;int j = 0;
    for (char ch = it.first(); ch != 65535; ch = it.next())
    {
      int v = k85d(ch);
      if (v >= 0)
      {
        i = i * 85L + v;
        j++;
        if (j >= 5)
        {
          out.write((int)(i >> 24));
          out.write((int)(i >> 16));
          out.write((int)(i >> 8));
          out.write((int)i);
          i = 0L;j = 0;
        }
      }
    }
    switch (j)
    {
    case 4: 
      i = i * 85L + 84L;
      out.write((int)(i >> 24));
      out.write((int)(i >> 16));
      out.write((int)(i >> 8));
      break;
    case 3: 
      i = i * 85L + 84L;
      i = i * 85L + 84L;
      out.write((int)(i >> 24));
      out.write((int)(i >> 16));
      break;
    case 2: 
      i = i * 85L + 84L;
      i = i * 85L + 84L;
      i = i * 85L + 84L;
      out.write((int)(i >> 24));
    }
    return out.toByteArray();
  }
  
  public static byte[] decodeLegacy85(String s)
  {
    int targetLength = -1;
    s = s.trim();
    if ((s.length() >= 7) && (s.charAt(0) == '<') && (s.charAt(6) == '>'))
    {
      targetLength = 
        k85d(s.charAt(1)) + 
        k85d(s.charAt(2)) * 85 + 
        k85d(s.charAt(3)) * 7225 + 
        k85d(s.charAt(4)) * 614125 + 
        k85d(s.charAt(5)) * 52200625;
      s = s.substring(7).trim();
    }
    CharacterIterator it = new StringCharacterIterator(s);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    long i = 0L;int j = 0;long k = 1L;
    for (char ch = it.first(); ch != 65535; ch = it.next())
    {
      int v = k85d(ch);
      if (v >= 0)
      {
        i += v * k;
        j++;
        k *= 85L;
        if (j >= 5)
        {
          out.write((int)i);
          out.write((int)(i >> 8));
          out.write((int)(i >> 16));
          out.write((int)(i >> 24));
          i = 0L;j = 0;k = 1L;
        }
      }
    }
    if (j > 0)
    {
      out.write((int)i);
      out.write((int)(i >> 8));
      out.write((int)(i >> 16));
      out.write((int)(i >> 24));
    }
    if (targetLength >= 0)
    {
      byte[] b = out.toByteArray();
      byte[] bt = new byte[targetLength];
      for (int x = 0; (x < targetLength) && (x < b.length); x++) {
        bt[x] = b[x];
      }
      return bt;
    }
    return out.toByteArray();
  }
  
  public static void main(String[] args)
    throws IOException
  {
    System.out.println("started");
    String s = "A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5A1B2C3D4E5";
    String encode = encodeASCII85(s.getBytes());
    System.out.println(encode);
    String decode = new String(decodeASCII85(encode));
    System.out.println(decode);
  }
}