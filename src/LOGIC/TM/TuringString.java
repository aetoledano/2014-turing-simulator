package LOGIC.TM;

public class TuringString {

    protected final int MAXN = 1000000;
    protected int END;
    protected int BEGIN;
    protected int post;
    protected int last;
    protected int show;
    protected final String original;
    protected char[] input;
    protected char[] output;

    public TuringString(String text) {
        this.BEGIN = 100000;
        this.END = BEGIN + text.length();
        this.post = BEGIN;
        this.last = BEGIN;
        this.show = 0;
        this.original = text;
        this.input = new char[MAXN];
        this.output = new char[MAXN];
        for (int i = BEGIN; i < END; i++) {
            input[i] = output[i] = text.charAt(i - BEGIN);
        }
    }

    public char Current() {
        return output[post];
    }

    public void Set(char ch) {
        output[post] = ch;
    }

    public void Next() {
        post++;
        if (post >= END) {
            output[post] = input[post] = '#';
            END++;
        }
    }

    public void Last() {
        post--;
        if (post < BEGIN) {
            output[post] = input[post] = '#';
            BEGIN--;
        }
    }

    public boolean Empty() {
        return (BEGIN == 50 || END == MAXN - 50);
    }

    public String getInput(int MAXL) {
        for (int i = post - MAXL; i < post + MAXL; i++) {
            if (i < BEGIN || i >= END) {
                output[i] = input[i] = '#';
            }
        }
        return String.copyValueOf(input, post - MAXL, 2 * MAXL + 1);
    }

    /*public String getOutput(int MAXL) {
        for (int i = post - MAXL; i < post + MAXL; i++) {
            if (i < BEGIN || i >= END) {
                output[i] = input[i] = '#';
            }
        }
        return String.copyValueOf(output, post - MAXL, 2 * MAXL + 1);
    }*/

    public String getOutput(int MAXL) {
        
        System.out.println("MAXL: "+MAXL+" post: "+post+" show: "+show+" last: "+last);
        
        if (post - last > 0) {
            show = (show + 1) % MAXL;
        }
        if (post - last < 0) {
            show = (show - 1 + MAXL) % MAXL;
        }
        last = post;
        
        for (int i = post - show; i < post + MAXL - show; i++) {
            if (i < BEGIN || i >= END) {
                output[i] = input[i] = '#';
            }
        }
        
        System.out.println("getOutput(MAXL) "+String.valueOf(output, post - show, MAXL));
        return String.valueOf(output, post - show, MAXL);
    }
    
    public int getPaint(){
        return show;
    }

    public String getOriginal() {
        return original;
    }

    public String getOutput() {
        return String.copyValueOf(output, BEGIN, END - BEGIN + 1);
    }

    public String getInput() {
        return String.copyValueOf(input, BEGIN, END - BEGIN + 1);
    }
}
