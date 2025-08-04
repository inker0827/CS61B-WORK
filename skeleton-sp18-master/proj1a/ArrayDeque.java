public class ArrayDeque<T> {

    private int size = 0;

    private int nextFirst;

    private int nextLast;

    private int item = 0;

    private T[] arrays;

    public ArrayDeque(){
        arrays=(T[])new Object[8];
        this.nextFirst=item+1;
        this.nextLast=item+2;
    }

    public void addFirst(T t) {
        if (this.size == this.arrays.length - 2) {
            resizeWithIncrease();
        }
        this.arrays[this.nextFirst] = t;

        if (this.nextFirst == 0) {
            nextFirst = size - 1;
        } else {
            this.nextFirst--;
        }
        size++;

    }
    public void addLast(T t) {
        if (this.size == this.arrays.length - 2) {
            resizeWithIncrease();
        }

        this.arrays[this.nextLast] = t;

        if (this.nextLast == this.arrays.length - 1) {
            nextLast = 0;
        } else {
            this.nextLast++;
        }
        size--;
    }


    public boolean isEmpty() {
        if(size==0){
            return true;
        }
        return  false;
    }

    public int size(){
        return this.size;
    }

    public void printDeque() {

        int first=nextFirst+1;
        int last=nextLast;
        while(true){
            System.out.print(this.arrays[first]);
            first++;
            if(first>arrays.length-1){
                first=0;
            }
            if(first==last){
                break;
            }
        }
        System.out.println();
    }

    private void resizeWithIncrease() {
        //从nextFirst->nextLast
        T[] newArray = (T[]) new Object[this.arrays.length * 2];
        int index = 0;

        while (true) {
            /*节点相邻
             如果 first 和 last 没有重合，则 first 不停+1直到走到数组右边界。
             如果是从后面部分开始走，走到右边界仍然未重合，则置为0从头继续找
             */
            if (this.nextFirst != this.nextLast) {
                //这里还有个特殊情况,就是nextFirst在尾部节点,
                if (this.nextFirst == this.arrays.length - 1) {
                    //如果nextFirst走到了数组右边的边界,就置于0,置于0就相当于+1,所以这里就不加一了
                    this.nextFirst = 0;
                } else {
                    //因为nextFirst是空位,所以要+1才有值的
                    this.nextFirst++;
                }
                //先+1是因为0位留给nextFirst,因为nextFirst要是空位
                if (this.arrays[this.nextFirst] != null) {
                    index++; //从1开始把0留给nextFirst
                    newArray[index] = this.arrays[this.nextFirst];
                }
            } else {
                break;
            }
        }

        this.nextFirst = 0;
        this.nextLast = index + 1; //最后再加一是因为nextLast要是空的
        this.arrays = newArray;
    }


    private void resizeIDecrease() {

        if (this.arrays.length < 16) {
            return;
        }

        if (size * 2 > this.arrays.length) {
            return;
        }
        //空置率至少一半,缩小25%
        int length = Double.valueOf(this.arrays.length * 0.75).intValue();
        T[] newArray = (T[]) new Object[length];
        int index = 0;

        while (true) {
            //为什么加一呢?因为nextFirst和nextLast是空的,加一如果相等意味着不能再往前了
            if (this.nextFirst != this.nextLast) {

                if (this.nextFirst == this.arrays.length - 1) {
                    //如果nextFirst走到了数组右边的边界,就置于0
                    this.nextFirst = 0;
                } else {
                    this.nextFirst++;
                }
                //先+1是因为0位留给nextFirst,因为nextFirst要是空位
                if (this.arrays[this.nextFirst] != null) {
                    index++;
                    newArray[index] = this.arrays[this.nextFirst];
                }
            } else {
                break;
            }
        }

        this.nextFirst = 0;
        this.nextLast = index + 1; //最后再加一是因为nextLast要是空的
        this.arrays = newArray;

    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        if (this.nextFirst == this.arrays.length - 1) {
            this.nextFirst = 0;

        }else {
            nextFirst++;
        }
        T t = this.arrays[this.nextFirst];
        this.arrays[this.nextFirst] = null;
        size--;
        resizeIDecrease();

        return t;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        if (this.nextLast== 0) {
            this.nextFirst = this.arrays.length - 1;

        }else {
            nextLast--;
        }
        T t = this.arrays[this.nextLast];
        this.arrays[this.nextLast] = null;
        size--;
        resizeIDecrease();

        return t;
    }







}