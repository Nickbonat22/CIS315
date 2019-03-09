import sys
from queue import Queue


class Graph:
    def __init__(self, size):
        self._size = size
        self._graph = {}
        self._paths = {}
        self._longest_path = None
        for i in range(1, size+1):
            self._graph[i] = {}

    def add_edge(self, vert1, vert2, weight):
        self._graph[vert1][vert2] = weight

    def find_longest(g, size):
        table = {}

        #initalize the dag
        for i in range(1, size + 1):
            table[i] = [0, 0]

        visited = []
        q = Queue()
        q.put(1)
        visited.append(1)
        table[1][1] = 1

        while q.empty() is not True:
            v = q.get()

            for u, weight in g[v].items():
                if u not in visited:
                    visited.append(u)
                    q.put(u)
                if table[v][0] + weight > table[u][0]:  # Reset current path multiplier
                    table[u][1] = 0
                    table[u][0] = table[v][0] + weight  # Update distance from v to u
                    table[u][1] += table[v][1]          # Replace (currently = 0) path multiplier
                elif table[v][0] + weight == table[u][0]:
                    table[u][1] += table[v][1]
        print("longest_path: {}".format(table[size][0]))
        print("number of longest paths: {}".format(table[size][1]))
        return table[size][0]

    def make_table(self, size):
        table = {}
        for i in range(1, size+1):
            table[i] = [0, 0]
        return table

def driver():
    f = sys.stdin
    V, E = (f.readline().strip().split(" "))   # Number of Vertices and Edges from the first line
    n = int(E)
    g = Graph(int(V))
    for _ in range(n):
        in_data = f.readline().strip().split()
        num1, num2, num3 = int(in_data[0]), int(in_data[1]), int(in_data[2])
        g.add_edge(num1, num2, num3)
    g.find_longest()

def driver():
    f = sys.stdin
    V, E = (f.readline().strip().split(" "))
    g = create_graph(int(V))

    for _ in range(int(E)):
        in_data = f.readline().strip().split()
        num1, num2, num3 = int(in_data[0]), int(in_data[1]), int(in_data[2])
        # add the nodes and weight to g
        add_edge(g,v1, v2, weight)

    longest_path(g, int(V))


if __name__ == "__main__":
    driver()